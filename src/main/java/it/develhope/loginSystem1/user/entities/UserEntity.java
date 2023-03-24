package it.develhope.loginSystem1.user.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Drumstyle92
 * entity class for the user in the system. The @Entity annotation
 * indicates that this class maps to a table in the database,
 * while the @Table annotation specifies the table name
 */
@Entity
@Table
public class UserEntity {

    /**
     * field "id" which represents the primary key of the table,
     * while @GeneratedValue specifies that the value of "id"
     * will be automatically generated
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    /**
     * Username field
     */
    @Column
    private String name;

    /**
     * Last name field
     */
    @Column
    private String surname;

    /**
     * User activation field
     */
    @Column
    private boolean isActive = false;

    /**
     * User activation code field
     */
    @Column(length = 36)
    private String activationCode;

    /**
     * User password reset code field
     */
    @Column(length = 36)
    private String passwordResetCode;

    /**
     * User email field
     */
    @Column(unique = true)
    private String email;

    /**
     * User password field
     */
    @Column
    private String password;

    /**
     * Time field of user created jwt token
     */
    @Column
    private LocalDateTime jwtCreatedOn;

    /**
     * many-to-many relationship between users and roles, so a user can have
     * more than one role and a role can be assigned to more than one user.
     * The relationship is mapped via the "USER_ROLES" junction table
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES", joinColumns =
    { @JoinColumn(name = "USER_ID") },
    inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") } )
    private Set<Role> roles;

    /**
     * Default constructor
     */
    public UserEntity(){}

    /**
     * @param id                the id
     * @param name              the name
     * @param surname           the surname
     * @param isActive          the is active
     * @param activationCode    the activation code
     * @param passwordResetCode the password reset code
     * @param email             the email
     * @param password          the password
     * @param jwtCreatedOn      the jwt created on
     * @param roles             the roles
     * Parameterized constructor
     */
    public UserEntity(long id, String name, String surname, boolean isActive,
                      String activationCode, String passwordResetCode,
                      String email, String password, LocalDateTime jwtCreatedOn, Set<Role> roles) {

        this.id = id;
        this.name = name;
        this.surname = surname;
        this.isActive = isActive;
        this.activationCode = activationCode;
        this.passwordResetCode = passwordResetCode;
        this.email = email;
        this.password = password;
        this.jwtCreatedOn = jwtCreatedOn;
        this.roles = roles;

    }

    /**
     * @return the id
     * Method for encapsulation
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id
     * Method for encapsulation
     */
    public void setId(long id) {
        this.id = id;
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
     * @return the boolean
     * Method for encapsulation
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * @param active the active
     * Method for encapsulation
     */
    public void setActive(boolean active) {
        isActive = active;
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

    /**
     * @return the password reset code
     * Method for encapsulation
     */
    public String getPasswordResetCode() {
        return passwordResetCode;
    }

    /**
     * @param passwordResetCode the password reset code
     * Method for encapsulation
     */
    public void setPasswordResetCode(String passwordResetCode) {
        this.passwordResetCode = passwordResetCode;
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

    /**
     * @return the jwt created on
     * Method for encapsulation
     */
    public LocalDateTime getJwtCreatedOn() {
        return jwtCreatedOn;
    }

    /**
     * @param jwtCreatedOn the jwt created on
     * Method for encapsulation
     */
    public void setJwtCreatedOn(LocalDateTime jwtCreatedOn) {
        this.jwtCreatedOn = jwtCreatedOn;
    }

    /**
     * @return the roles
     * Method for encapsulation
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles
     * Method for encapsulation
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}


