package it.develhope.loginSystem1.notification.services;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import it.develhope.loginSystem1.user.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;

/**
 * @author Drumstyle92
 * class service that provides methods for sending email using the SendGrid API
 */
@Service
public class MailNotificationService {

    /**
     * SendGrid object which is injected via the @Autowired annotation.
     * This object represents the connection to the SendGrid API and
     * provides methods for sending emails
     */
    @Autowired
    private SendGrid sendGrid;

    /**
     * @param user parameter that takes a user entity containing
     *             the user's email and the system-generated activation code
     * method that sends an activation email to the newly registered user.
     * If sending the email fails, an error message is printed to the console
     */
    public void sendActivationEmail(UserEntity user) {

        Email from = new Email("YOUR_EMAIL");

        String subject = "You have signed up to the platform";

        Email to = new Email(user.getEmail());

        Content content = new Content("text/plain", "The activation code is:" + user.getActivationCode());

        Mail mail = new Mail(from, subject, to, content);

        Request request = new Request();

        try {

            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sendGrid.api(request);

            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());

        } catch (IOException ex) {

            System.out.println("ERROR ACTIVATION");

            ex.fillInStackTrace();

        }

    }

    /**
     * @param user Parameter which takes the instance of "UserEntity"
     *             which represents the user who requested the password reset
     * method used to send a password reset email to a specific user.
     * If there is an error, an error message is printed and an exception is thrown
     */
    public void sendPasswordResetMail(UserEntity user) {

        Email from = new Email("YOUR_EMAIL");

        String subject = "Password reset successful";

        Email to = new Email(user.getEmail());

        Content content = new Content("text/plain", "The new password is: " + user.getPasswordResetCode());

        Mail mail = new Mail(from, subject, to, content);

        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sendGrid.api(request);

            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());

        } catch (IOException ex) {

            System.out.println("ERROR RESET");

            ex.fillInStackTrace();

        }

    }

}