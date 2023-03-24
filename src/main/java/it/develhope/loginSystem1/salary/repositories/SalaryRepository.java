package it.develhope.loginSystem1.salary.repositories;

import it.develhope.loginSystem1.salary.entities.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Drumstyle92
 * interface that can be used to perform persistence operations on the database for the Salary entity
 */
public interface SalaryRepository extends JpaRepository<Salary, Long> {


    /**
     * @param userId Parameter that takes the selected id
     * @return Returns the salary of the selected user
     * method for retrieving a user's salary by his ID
     */
    Salary findByUserId(Long userId);


}

