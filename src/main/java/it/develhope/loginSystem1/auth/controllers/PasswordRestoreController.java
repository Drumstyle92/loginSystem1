package it.develhope.loginSystem1.auth.controllers;

import it.develhope.loginSystem1.auth.entities.RequestPasswordDTO;
import it.develhope.loginSystem1.auth.entities.RestorePasswordDTO;
import it.develhope.loginSystem1.auth.services.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Drumstyle92
 * REST controller class used to handle user password reset requests
 */
@RestController
@RequestMapping("/auth/password")
public class PasswordRestoreController {

    /**
     * Dependency with @Autowired annotation to inject the PasswordService into the controller
     */
    @Autowired
    private PasswordService passwordService;

    /**
     * @param requestPasswordDTO Parameter that takes an object of the RequestPasswordDTO
     *                           type which contains the email address of the user requesting password recovery
     * @throws Exception the exception
     * method that is called when the user sends a password reset request via an HTTP POST request
     */
    @PostMapping("/request")
    public void passwordRequest(@RequestBody RequestPasswordDTO requestPasswordDTO) throws Exception{

        try {

            passwordService.request(requestPasswordDTO);

        }catch (Exception e){

        }

    }

    /**
     * @param restorePasswordDTO Parameter that takes an object of type RestorePasswordDTO
     *                           which contains the user's new password and the password recovery code
     * @throws Exception the exception
     * method that is called when the user submits a new password for recovery via an HTTP POST request
     */
    @PostMapping("/restore")
    public void passwordRestore(@RequestBody RestorePasswordDTO restorePasswordDTO) throws Exception{

        passwordService.restore(restorePasswordDTO);

    }

}
