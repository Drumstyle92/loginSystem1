package it.develhope.loginSystem1.auth.entities;


/**
 * @author Drumstyle92
 * class used to manage the user login process
 */
public class LoginDTO {

    /** This is the user email */
    private String email;
    /** This is the password CLEAR */
    private String password;

    /**
     * Default constructor
     */
    public LoginDTO(){}

    /**
     * @param email    the email
     * @param password the password
     * Parameterized constructor
     */
    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * @return the email
     * Method for encapsulation
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email
     * Method for encapsulation
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     * Method for encapsulation
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password
     * Method for encapsulation
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
