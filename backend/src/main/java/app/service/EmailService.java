package app.service;

import app.utils.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private static final String UTF_8 = "UTF-8";
    private final JavaMailSender mailSender;

    @Value("${spring.mail.verify.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String from;

    void sendSimpleMessage(String name, String to, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Spring app - Email activation link");
        message.setFrom(from);
        message.setTo(to);
        message.setText(EmailUtils.getEmailMessage(name, host, token));

        mailSender.send(message);
    }
}
