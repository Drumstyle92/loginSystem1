package it.develhope.loginSystem1.salary.controllers;


import it.develhope.loginSystem1.salary.entities.CreateSalaryDTO;
import it.develhope.loginSystem1.salary.entities.Salary;
import it.develhope.loginSystem1.user.entities.UserEntity;
import it.develhope.loginSystem1.salary.repositories.SalaryRepository;
import it.develhope.loginSystem1.user.repositories.UserRepository;
import it.develhope.loginSystem1.utils.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * @author Drumstyle92
 * REST controller class to handle user payroll operations
 */
@RestController
@RequestMapping("/salary")
public class SalaryController {

    /**
     * Autowired annotated dependency that represents a
     * JPA repository instance for managing Salary objects in the database
     */
    @Autowired
    SalaryRepository salaryRepository;

    /**
     * Autowired annotated dependency representing a
     * JPA repository instance for handling UserEntity objects in the database
     */
    @Autowired
    UserRepository userRepository;

    /**
     * @param id Parameter that takes the selected id
     * @return Returns the list of all salaries
     * get method which takes the list of all salaries in the database. This method requires the ADMIN role to run
     */
    @GetMapping("/userAll/{id}")
    @PreAuthorize("hasRole('"+ Roles.ADMIN +"')")
    public List<Salary> getAll(@PathVariable Long id){
        return salaryRepository.findAll();
    }

    /**
     * @param userId Selected id parameter
     * @return Returns the salary and id of the selected user
     * method get which is used to display the salary
     * of the user with the specified ID. This method
     * requires the ADMIN role to run
     */
    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('"+ Roles.ADMIN +"')")
    public Salary getSalaryByUid(@PathVariable Long userId){

        return salaryRepository.findByUserId(userId);

    }

    /**
     * @param id        Selected id parameter
     * @param salaryDTO Parameter that takes the CreateSalaryDTO object
     * @return Returns the user with a new salary
     * Post method which creates a new salary for the user
     * with the specified ID using the data provided by the
     * CreateSalaryDTO object passed in the HTTP request.
     * This method requires the ADMIN role to run
     */
    @PostMapping("/user/{id}")
    @PreAuthorize("hasRole('"+ Roles.ADMIN +"')")
    public Salary createSalary(@PathVariable Long id, @RequestBody CreateSalaryDTO salaryDTO){

        Salary s = new Salary();
        s.setAmount(salaryDTO.getAmount());

        Optional<UserEntity> userS = userRepository.findById(id);
        s.setUserEntity(userS.get());

        return salaryRepository.save(s);

    }

}

