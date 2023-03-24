package it.develhope.loginSystem1.salary.entities;

import it.develhope.loginSystem1.user.entities.UserEntity;
import javax.persistence.*;

/**
 * @author Drumstyle92
 * class representing an employee's salary and be stored in a relational database
 */
@Entity
@Table
public class Salary {

    /**
     * Field that is generated in sequence and represents the ID of the user who receives the salary
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;

    /**
     * Field that represents the salary amount
     */
    @Column
    private long amount;

    /**
     * Field representing the entity of the user who receives the salary,
     * N to N relationship
     */
    @OneToOne
    private UserEntity userEntity;


    /**
     *  Default constructor
     */
    public Salary(){}

    /**
     * @param id         the id
     * @param amount     the amount
     * @param userEntity the user entity
     * Parameterized constructor
     */
    public Salary(long id, long amount, UserEntity userEntity) {
        this.userId = id;
        this.amount = amount;
        this.userEntity = userEntity;
    }

    /**
     * @return the id
     * Method for encapsulation
     */
    public Long getId() {
        return userId;
    }

    /**
     * @param id the id
     * Method for encapsulation
     */
    public void setId(Long id) {
        this.userId = id;
    }

    /**
     * @return the amount
     * Method for encapsulation
     */
    public long getAmount() {
        return amount;
    }

    /**
     * @param amount the amount
     * Method for encapsulation
     */
    public void setAmount(long amount) {
        this.amount = amount;
    }

    /**
     * @return the user entity
     * Method for encapsulation
     */
    public UserEntity getUserEntity() {
        return userEntity;
    }

    /**
     * @param userEntity the user entity
     * Method for encapsulation
     */
    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

}