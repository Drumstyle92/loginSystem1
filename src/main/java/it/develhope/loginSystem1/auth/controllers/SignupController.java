package it.develhope.loginSystem1.auth.controllers;

import it.develhope.loginSystem1.auth.entities.SignupActivationDTO;
import it.develhope.loginSystem1.auth.entities.SignupDTO;
import it.develhope.loginSystem1.auth.services.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Drumstyle92
 * REST controller class used to handle user registration requests
 */
@RestController
@RequestMapping("/auth")
public class SignupController {

    /**
     * Depend with @Autowired annotation to inject a dependency
     * of the SignupService service inside the controller
     */
    @Autowired
    private SignupService signupService;

    /**
     * @param signupDTO Parameter that takes an object of type SignupDTO
     *                  in which it contains the user's registration information
     * @throws Exception the exception
     * method that is called when the user sends a registration request to the system via an HTTP POST request
     */
    @PostMapping("/signup")
    public void signup(@RequestBody SignupDTO signupDTO) throws Exception {

        signupService.signup(signupDTO);

    }

    /**
     * @param signupActivationDTO parameter which takes a SignupActivationDTO
     *                            object which contains the user's account activation code
     * @throws Exception the exception
     * method that is called when the user sends an account activation request via an HTTP POST request
     */
    @PostMapping("/signup/activation")
    public void signup(@RequestBody SignupActivationDTO signupActivationDTO) throws Exception {

        signupService.activate(signupActivationDTO);

    }

}
