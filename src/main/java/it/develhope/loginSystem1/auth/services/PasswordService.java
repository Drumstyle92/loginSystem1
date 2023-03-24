package it.develhope.loginSystem1.auth.services;

import it.develhope.loginSystem1.auth.entities.RequestPasswordDTO;
import it.develhope.loginSystem1.auth.entities.RestorePasswordDTO;
import it.develhope.loginSystem1.notification.services.MailNotificationService;
import it.develhope.loginSystem1.user.entities.UserEntity;
import it.develhope.loginSystem1.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * @author Drumstyle92
 * class that provides methods for managing user passwords
 */
@Service
public class PasswordService {

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
     * @param requestPasswordDTO Parameter that takes a RequestPasswordDTO object that contains the user's email
     * @return restituisce l'entità UserEntity
     * @throws Exception the exception
     * Method that takes as input a RequestPasswordDTO object
     * containing the user's email and returns the UserEntity
     * entity related to that user after generating a password
     * recovery code and sending an email containing the code
     * to the user via MailNotificationService
     */
    public UserEntity request(RequestPasswordDTO requestPasswordDTO) throws Exception{

        UserEntity userFromDB = userRepository.findByEmail(requestPasswordDTO.getEmail());

        if(userFromDB == null) {

            throw new Exception("Cannot find user");

        }

        userFromDB.setPasswordResetCode(UUID.randomUUID().toString());

        mailNotificationService.sendPasswordResetMail(userFromDB);

        return userRepository.save(userFromDB);

    }

    /**
     * @param restorePasswordDTO Parameter that takes a RestorePasswordDTO object
     *                           that contains the password recovery code and the user's new password
     * @return Returns the updated user object
     * @throws Exception the exception
     * Method that searches the database for the matching user
     * by password recovery code, and if the user is found,
     * updates the password, removes the recovery code, and
     * activates the user's account by setting the active
     * field to true and removing the activationCode field
     */
    public UserEntity restore(RestorePasswordDTO restorePasswordDTO) throws Exception{

        UserEntity userFromDB = userRepository.findByPasswordResetCode(restorePasswordDTO.getResetPasswordCode());

        if(userFromDB == null) {

            throw new Exception("Cannot find user");

        }

        userFromDB.setPassword(passwordEncoder.encode(restorePasswordDTO.getNewPassword()));

        userFromDB.setPasswordResetCode(null);

        //I am activating the user
        userFromDB.setActive(true);

        userFromDB.setActivationCode(null);

        return userRepository.save(userFromDB);

    }

}

