package it.develhope.loginSystem1;

import it.develhope.loginSystem1.auth.services.LoginService;
import it.develhope.loginSystem1.user.entities.UserEntity;
import it.develhope.loginSystem1.user.repositories.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @author Drumstyle92
 * class used to filter incoming HTTP requests and check the validity of the JWT token
 */
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    /**
     * userRepository field of type UserRepository, which is injected with the @Autowired annotation
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * @param request Parameter that takes the HttpServletRequest object
     *                representing the incoming HTTP request to be filtered
     * @param response Parameter that takes the HttpServletResponse object
     *                 representing the HTTP response that will be returned
     *                 to the client after the request has been filtered
     * @param chain Parameter that takes the FilterChain object representing
     *              the chain of filters to run for the current request
     * @throws ServletException
     * @throws IOException
     * method for executing the filtering logic. The method checks if the authorization
     * header contains a valid JWT token and decrypts it using the HMAC512 algorithm and
     * the secret key provided by the login service. If the token is valid, the user's
     * identity is retrieved from the database using the token's "id" claim and is set to
     * the Spring security context. Finally, the request is filtered and forwarded to the
     * next filter chain If the token is invalid or the user's identity is missing or inactive,
     * the request is simply forwarded to the next filter chain without setting the user's identity
     * to the Spring security context
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        // Get authorization header and validate
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null) {

            chain.doFilter(request, response);

            return;

        }

        // Get jwt token and validate
        final String token = header.trim();

        DecodedJWT decoded;

        try {

            JWTVerifier verifier = JWT.require(Algorithm.HMAC512(LoginService.JWT_SECRET)).withIssuer("develhope-Dino").build();

            decoded = verifier.verify(token);

        }catch (JWTVerificationException ex){

            chain.doFilter(request, response);

            return;

        }

        // Get user identity and set it on the spring security context
        Optional<UserEntity> userDetails = userRepository.findById(decoded.getClaim("id").asLong());

        if (userDetails.isEmpty() || !userDetails.get().isActive()) {

            chain.doFilter(request, response);

            return;

        }

        UserEntity userEntity = userDetails.get();
        userEntity.setPassword(null);
        userEntity.setActivationCode(null);
        userEntity.setPasswordResetCode(null);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userEntity, null, List.of() );

        authentication.setDetails( new WebAuthenticationDetailsSource().buildDetails(request) );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);

    }

}
