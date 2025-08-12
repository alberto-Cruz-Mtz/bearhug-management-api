package site.bearhug.management.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import site.bearhug.management.presentation.dto.model.Email;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
@Slf4j
public class MailSender {

    private final JavaMailSender sender;

    @Value("${spring.mail.username}")
    public String fromUsername;

    public void sendMailVerification(Email email) {
        try {
            ClassPathResource resource = new ClassPathResource("templates/verify.html");
            String htmlTemplate = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
            String htmlContent = htmlTemplate.replace("${username}", email.toUsername());
            
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
            
            helper.setFrom(fromUsername);
            helper.setTo(email.toUsername());
            helper.setSubject(email.subject());
            helper.setText(htmlContent, true); // true indicates HTML content
            
            sender.send(message);
            log.info("Verification email sent to: {}", email.toUsername());
            
        } catch (MessagingException | IOException e) {
            log.error("Error sending verification email to: {}", email.toUsername(), e);
            throw new RuntimeException("Failed to send verification email", e);
        }
    }
}
