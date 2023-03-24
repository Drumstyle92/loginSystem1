package it.develhope.loginSystem1.auth.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import it.develhope.loginSystem1.auth.entities.LoginDTO;
import it.develhope.loginSystem1.auth.entities.LoginRTO;
import it.develhope.loginSystem1.user.entities.UserEntity;
import it.develhope.loginSystem1.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Drumstyle92
 * service class called "LoginService" which manages user authentication
 */
@Service
public class LoginService {

    /**
     * JWT_SECRET constant representing a secret key
     * used to sign JWT tokens. It is important to
     * keep this secret key secure and never make it public
     */
    public static final String JWT_SECRET="YOUR_CODE";

    /**
     * Automated dependency of PasswordEncoder instance to
     * encrypt user password before saving to database
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Autowired annotated dependency representing a
     * JPA repository instance for handling UserEntity objects in the database
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * @param loginDTO Parameter that takes a "loginDTO" object that contains
     *                 the email address and password of the user trying to Log
     * @return returns a "LoginRTO" object containing a JWT (JSON Web Token) and authenticated user data
     * @throws UnsupportedEncodingException the unsupported encoding exception
     * method which is used to verify a user's login credentials,
     * generate a JWT auth token and return the "LoginRTO" object
     * which contains both the JWT token and the authenticated user
     */
    public LoginRTO login(LoginDTO loginDTO) throws UnsupportedEncodingException {

        if(loginDTO == null) {

            return null;

        }

        UserEntity userFromDB = userRepository.findByEmail(loginDTO.getEmail());

        if(userFromDB == null || !userFromDB.isActive()) {

            return null;

        }

        boolean canLogin = this.canUserLogin(userFromDB, loginDTO.getPassword());

        if(!canLogin) {

            return null;

        }

        String JWT = generateJWT(userFromDB);

        userFromDB.setPassword(null);

        LoginRTO out = new LoginRTO();
        out.setJWT(JWT);
        out.setUser(userFromDB);

        return out;

    }

    /**
     * @param userEntity parameter which takes a "userEntity" object representing the user
     * @param password   Parameter that takes a "password" string representing
     *                   the password that the user entered during the login attempt
     * @return the boolean
     * Method that checks if the password entered by the user
     * matches the password stored for the user, and returns
     * "true" if the password is correct, "false" otherwise
     */
    public boolean canUserLogin(UserEntity userEntity, String password){

        return passwordEncoder.matches(password, userEntity.getPassword());

    }

    /**
     * @param dateToConvert Parameter that takes a LocalDateTime object
     * @return Returns a Java Date object
     * Method that converts a LocalDateTime object into a Java Date object,
     * which can be useful in certain situations where a Date object is required
     * for compatibility with other parts of the code
     */
    static Date convertToDateViaInstant(LocalDateTime dateToConvert) {

        return java.util.Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());

    }

    /**
     * @param user Parameter that takes the reference userEntity object
     * @return The JWT returns
     * static getJWT method that creates and returns a JWT
     */
    public static String getJWT(UserEntity user) {

        Date expiresAt = convertToDateViaInstant(LocalDateTime.now().plusDays(15));

        return JWT.create()
                .withIssuer("develhope-Dino")
                .withIssuedAt(new Date())
                .withExpiresAt(expiresAt)
                .withClaim("id", user.getId())
                .sign(Algorithm.HMAC512(JWT_SECRET));

    }


    /**
     * @param user Parameter that takes a UserEntity object
     * @return Returns the UserEntity object, with the password set to null
     * @throws UnsupportedEncodingException the unsupported encoding exception
     * method generateJWT generates a signed JSON Web Token (JWT) that contains
     * user information and an expiration date. The token is then returned to
     * the client along with a copy of the UserEntity object, with the password set to null
     */
    public String generateJWT(UserEntity user) throws UnsupportedEncodingException {

        String JWT = getJWT(user);

        user.setJwtCreatedOn(LocalDateTime.now());

        userRepository.save(user);

        return JWT;

    }

}

