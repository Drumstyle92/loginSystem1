package it.develhope.loginSystem1.auth.entities;

/**
 * @author Drumstyle92
 * DTO class used to store user registration information
 */
public class SignupDTO {

    /** User name */
    private String name;

    /** User surname */
    private String surname;

    /** User email */
    private String email;

    /** User password clear */
    private String password;

    /**
     * Default constructor
     */
    public SignupDTO(){}

    /**
     * @param name     the name
     * @param surname  the surname
     * @param email    the email
     * @param password the password
     * Parameterized constructor
     */
    public SignupDTO(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    /**
     * @return the name
     * Method for encapsulation
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name
     * Method for encapsulation
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the surname
     * Method for encapsulation
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname
     * Method for encapsulation
     */
    public void setSurname(String surname) {
        this.surname = surname;
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
     *
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     *
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password
     *
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
