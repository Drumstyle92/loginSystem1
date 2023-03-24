package it.develhope.loginSystem1.auth.entities;

import it.develhope.loginSystem1.user.entities.UserEntity;

/**
 * @author Drumstyle92
 * DTO class used to manage the user authentication process
 */
public class LoginRTO {

    /**
     * Field containing the user entity corresponding to the user authenticated in the system
     */
    private UserEntity user;

    /**
     * Field containing the JSON Web Token authentication token,
     * which is used to authenticate the user securely and reliably.
     * Used by the system to verify the user's authenticity each
     * time he accesses the services offered by the system
     */
    private String JWT;

    /**
     * Default constructor
     */
    public LoginRTO(){}

    /**
     * @param user the user
     * @param JWT  the jwt
     * Parameterized constructor
     */
    public LoginRTO(UserEntity user, String JWT) {
        this.user = user;
        this.JWT = JWT;
    }

    /**
     * @return the user
     * Method for encapsulation
     */
    public UserEntity getUser() {
        return user;
    }

    /**
     * @param user the user
     * Method for encapsulation
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }

    /**
     * @return the jwt
     * Method for encapsulation
     */
    public String getJWT() {
        return JWT;
    }

    /**
     * @param JWT the jwt
     * Method for encapsulation
     */
    public void setJWT(String JWT) {
        this.JWT = JWT;
    }

}
