package it.develhope.loginSystem1.user.entities;

import javax.persistence.*;

/**
 * @author Drumstyle92
 * class representing a role entity that maps to a database table using JPA annotations
 */
@Entity
@Table
public class Role {

    /**
     * Role's Id field is auto-generated using sequential generation strategy
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;

    /**
     * Role name field
     */
    @Column
    private String name;

    /**
     * Role description field
     */
    @Column
    private String description;

    /**
     * Default constructor
     */
    public Role(){}

    /**
     * @param id          the id
     * @param name        the name
     * @param description the description
     * Parameterized constructor
     */
    public Role(Long id, String name, String description) {
        Id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * @return the salary id
     * Method for encapsulation
     */
    public Long getId() {
        return Id;
    }

    /**
     * @param id the salary id
     * Method for encapsulation
     */
    public void setId(Long id) {
        Id = id;
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
     * @return the description
     * Method for encapsulation
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description
     * Method for encapsulation
     */
    public void setDescription(String description) {
        this.description = description;
    }

}

