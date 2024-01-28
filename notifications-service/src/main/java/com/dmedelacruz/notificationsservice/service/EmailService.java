package com.dmedelacruz.notificationsservice.service;

import com.dmedelacruz.storemodel.notification.NotificationEmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public Boolean emailUser(NotificationEmailRequest emailRequest) {
        System.out.println("EMAIL CUSTOMER SUCCESS");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("temporal-demo@dmedelacruz.com");
        mailMessage.setTo("dandelacruz@umpisa.co");
        mailMessage.setSubject(emailRequest.getSubject());
        String message = "Customer: " + emailRequest.getCustomerId() + "\n" + emailRequest.getMessage();
        mailMessage.setText(message);

        javaMailSender.send(mailMessage);

        return true;
    }

}
