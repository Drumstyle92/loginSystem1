package it.develhope.loginSystem1.auth.services;

import it.develhope.loginSystem1.auth.entities.SignupActivationDTO;
import it.develhope.loginSystem1.auth.entities.SignupDTO;
import it.develhope.loginSystem1.notification.services.MailNotificationService;
import it.develhope.loginSystem1.user.entities.UserEntity;
import it.develhope.loginSystem1.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * @author Drumstyle92
 * Class that manages user registration operations and account activation by sending activation emails
 */
@Service
public class SignupService {

    /**
     * Autowired annotated dependency representing a
     * JPA repository instance for handling UserEntity objects in the database
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Automated dependency of MailNotificationService
     * instance to send account activation email to user
     */
    @Autowired
    private MailNotificationService mailNotificationService;

    /**
     * Automated dependency of PasswordEncoder instance to
     * encrypt user password before saving to database
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * @param signupDTO Parameter that takes a SignupDTO object
     * @return returns a UserEntity instance
     * @throws Exception the exception
     * method that checks if a user with the same email address
     * already exists in the database. If the user does not exist,
     * a new UserEntity object representing the registered user is
     * created and saved in the database. An activation email is
     * then sent to the registered user's email address
     */
    public UserEntity signup(SignupDTO signupDTO) throws Exception {

        UserEntity userInDB = userRepository.findByEmail(signupDTO.getEmail());

        if(userInDB != null) {

            throw new Exception("User already Exists");

        }

        UserEntity user = new UserEntity();
        user.setName(signupDTO.getName());
        user.setEmail(signupDTO.getEmail());
        user.setSurname(signupDTO.getSurname());
        user.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        user.setActivationCode(UUID.randomUUID().toString());

        mailNotificationService.sendActivationEmail(user);

        return userRepository.save(user);

    }

    /**
     * @param signupActivationDTO Parameter that takes a SignupActivationDTO object
     * @return returns a UserEntity instance
     * @throws Exception the exception
     * method that looks for a user with the same account activation
     * code in the database. If the user was found, their account is
     * activated and the activation code is set to null. Finally,
     * the changes made to the user's account are saved in the database
     */
    public UserEntity activate(SignupActivationDTO signupActivationDTO) throws Exception {

        UserEntity user = userRepository.findByActivationCode(signupActivationDTO.getActivationCode());

        if(user == null) {

            throw new Exception("User Not found");

        }

        user.setActive(true);
        user.setActivationCode(null);

        return userRepository.save(user);

    }

}

