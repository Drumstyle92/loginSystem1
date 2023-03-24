package it.develhope.loginSystem1.auth.entities;

/**
 * @author Drumstyle92
 * DTO class used to reset the user's password
 */
public class RestorePasswordDTO {

    /**
     * Field representing the new password that the user wants to set after resetting the password
     */
    private String newPassword;

    /**
     * Field representing a unique recovery code associated with your account,
     * which is used to confirm your identity and allow you to reset your account password
     */
    private String resetPasswordCode;

    /**
     * Default constructor
     */
    public RestorePasswordDTO(){
    }

    /**
     * @param newPassword       the new password
     * @param resetPasswordCode the reset password code
     * Parameterized constructor
     */
    public RestorePasswordDTO(String newPassword, String resetPasswordCode) {
        this.newPassword = newPassword;
        this.resetPasswordCode = resetPasswordCode;
    }

    /**
     * @return the new password
     * Method for encapsulation
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword the new password
     * Method for encapsulation
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * @return the reset password code
     * Method for encapsulation
     */
    public String getResetPasswordCode() {
        return resetPasswordCode;
    }

    /**
     * @param resetPasswordCode the reset password code
     * Method for encapsulation
     */
    public void setResetPasswordCode(String resetPasswordCode) {
        this.resetPasswordCode = resetPasswordCode;
    }

}

