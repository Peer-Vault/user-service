package com.peer.vault.user_service.service;

import com.peer.vault.user_service.client.NotificationClient;
import com.peer.vault.user_service.domain.UserCredential;
import com.peer.vault.user_service.dto.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private NotificationClient notificationClient;

    public void sendRegistrationSuccessEmail(UserCredential credential) {
        String firstName = credential.getFirstName();
        String lastName = credential.getLastName();
        String username = credential.getUsername();
        String email = credential.getEmail();
        String phoneNumber = credential.getPhoneNumber();

        String emailBody = String.format(
                "Dear %s %s,\n\n" +
                        "Congratulations on successfully registering with Peer Vault! We are thrilled to have you as part of our community.\n\n" +
                        "Here are your registration details:\n" +
                        "- **Username:** %s\n" +
                        "- **Email:** %s\n" +
                        "- **Phone Number:** %s\n\n" +
                        "We believe that you will find immense value in the services we offer and are committed to providing you with the best possible experience. If you have any questions or need assistance, please do not hesitate to reach out to us.\n\n" +
                        "For support, you can contact us at:\n" +
                        "- **Phone:** 8955946276\n" +
                        "- **Email:** cmanishkumar193@gmail.com\n\n" +
                        "Thank you for choosing Peer Vault. We look forward to supporting you on your journey.\n\n" +
                        "Best regards,\n\n" +
                        "The Peer Vault Team"
                , firstName, lastName, username, email, phoneNumber);

        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setTo(email);
        emailRequest.setSubject("Registration Successful");
        emailRequest.setBody(emailBody);

        notificationClient.sendEmail(emailRequest);
    }

}
