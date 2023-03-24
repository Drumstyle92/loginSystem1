package it.develhope.loginSystem1.auth.entities;

/**
 * @author Drumstyle92
 * DTO class used to request user password reset
 */
public class RequestPasswordDTO {

    /**
     * Field containing the email address of the user who requested password reset.
     * The system will use this email address to send you a unique recovery code
     * that will allow you to reset your password
     */
    private String email;

    /**
     * Default constructor
     */
    public RequestPasswordDTO(){}

    /**
     * @param email the email
     * Parameterized constructor
     */
    public RequestPasswordDTO(String email) {
        this.email = email;
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

}
