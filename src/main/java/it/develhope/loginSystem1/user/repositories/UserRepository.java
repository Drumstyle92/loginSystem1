package it.develhope.loginSystem1.user.repositories;

import it.develhope.loginSystem1.user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Drumstyle92
 * interface to a data repository that manages the UserEntity entity
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * @param email Parameter that takes the selected email
     * @return returns a UserEntity instance with the specified email
     * method which has no optional arguments and returns null if they cannot find a matching instance
     */
    UserEntity findByEmail(String email);

    /**
     * @param activationCode Parameter that takes a string of code for activation
     * @return returns a UserEntity instance with the specified activation code
     * method which has no optional arguments and returns null if they cannot find a matching instance
     */
    UserEntity findByActivationCode(String activationCode);

    /**
     * @param passwordResetCode Parameter that takes a string of code for resetting the password
     * @return returns a UserEntity instance with the specified password reset code
     * method which has no optional arguments and returns null if they cannot find a matching instance
     */
    UserEntity findByPasswordResetCode(String passwordResetCode);

}
