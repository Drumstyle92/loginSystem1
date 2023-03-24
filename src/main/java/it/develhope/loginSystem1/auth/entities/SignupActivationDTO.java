package it.develhope.loginSystem1.auth.entities;

/**
 * @author Drumstyle92
 * DTO class used to activate the user's account
 */
public class SignupActivationDTO {

    /**
     * field containing the unique activation code associated with the user's account,
     * which will be used to confirm the account and allow the user to access the services offered by the system
     */
    private String activationCode;

    /**
     * Default constructor
     */
    public SignupActivationDTO(){}

    /**
     * @param activationCode the activation code
     * Parameterized constructor
     */
    public SignupActivationDTO(String activationCode) {
        this.activationCode = activationCode;
    }

    /**
     * @return the activation code
     * Method for encapsulation
     */
    public String getActivationCode() {
        return activationCode;
    }

    /**
     * @param activationCode the activation code
     * Method for encapsulation
     */
    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

}

