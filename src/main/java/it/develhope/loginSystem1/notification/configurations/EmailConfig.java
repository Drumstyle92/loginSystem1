package it.develhope.loginSystem1.notification.configurations;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Drumstyle92
 * Class representing a configuration for sending email using the SendGrid third-party service
 */
@Configuration
public class EmailConfig {

    /**
     * String representing the SendGrid API key used to authenticate email sending API requests
     */
    @Value("${spring.email.sendgrid.api-key}")
    private String sendGridApiKey;

    /**
     * @return returns a SendGrid instance
     * method representing a Java client for sending email
     * via the SendGrid service. This method uses the SendGrid
     * API key to authenticate API requests
     */
    @Bean
    public SendGrid sendGrid() {
        return new SendGrid(sendGridApiKey);
    }

}