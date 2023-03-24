package it.develhope.loginSystem1.user.controllers;

import it.develhope.loginSystem1.auth.entities.LoginRTO;
import it.develhope.loginSystem1.auth.services.LoginService;
import it.develhope.loginSystem1.user.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.UnsupportedEncodingException;
import java.security.Principal;

/**
 * @author Drumstyle92
 * REST controller class for user profile management
 */
@RestController
public class UserController {

    /**
     *  Dependency with Autowired annotation to take advantage of login-related services
     */
    @Autowired
    LoginService loginService;

    /**
     * @param principal Parameter that takes the Principal object representing the authenticated user
     * @return Returns the user object and the generated Jwt
     * @throws UnsupportedEncodingException the unsupported encoding exception
     * GET method to retrieve the user's profile. Authentication is handled automatically
     * by the Spring Security framework. The method receives the Principal object, which
     * represents the authenticated user, and uses it to retrieve the corresponding UserEntity
     * object. Next, a JSON Web Token (JWT) is generated using the LoginService service and
     * the UserEntity object. Finally, the method returns a LoginRTO (Login Response Transfer Object)
     * which contains the UserEntity object and the generated JWT token
     */
    @GetMapping("/profile")
    public LoginRTO getProfile(Principal principal ) throws UnsupportedEncodingException {

        UserEntity user = (UserEntity) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        LoginRTO rto = new LoginRTO();
        rto.setUser(user);
        rto.setJWT(loginService.generateJWT(user));

        return rto;

    }

}

