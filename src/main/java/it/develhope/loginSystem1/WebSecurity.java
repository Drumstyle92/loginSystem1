package it.develhope.loginSystem1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Drumdtyle92
 * Java class called WebSecurity which extends the WebSecurityConfigurerAdapter
 * class, used to configure Spring Security for a web application. The class is
 * annotated with @EnableWebSecurity to enable Spring web security configuration
 */
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    /**
     * Auto-nested dependency of the JwtTokenFilter class
     */
    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    /**
     * @return returns a BCryptPassword object
     * method that returns a BCryptPasswordEncoder object used to encrypt and decrypt passwords
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @param http Parameter that takes the modifications of the HttpSecurity object
     * @throws Exception
     * method that sets the session creation policy to STATELESS
     * and defines permissions for HTTP requests. It also disables
     * CSRF (Cross-Site Request Forgery) and frameOptions settings
     * for using the H2 console. Finally, it adds a JwtTokenFilter
     * to check for user authentication using the JWT token
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable();

        // Add JWT token filter
        http.addFilterBefore( jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }

}


