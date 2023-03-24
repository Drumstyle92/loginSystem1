package it.develhope.loginSystem1.salary.entities;

/**
 * @author Drumstyle92
 * DTO class that represents an object used to create a new salary
 */
public class CreateSalaryDTO {

    /**
     * Field representing the salary amount
     */
    long amount;

    /**
     * @param amount the amount
     * Parameterized constructor
     */
    public CreateSalaryDTO(long amount) {
        this.amount = amount;
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

}

