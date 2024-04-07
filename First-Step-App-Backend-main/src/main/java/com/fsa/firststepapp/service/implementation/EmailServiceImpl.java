package com.fsa.firststepapp.service.implementation;

import com.fsa.firststepapp.service.email_service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(
            String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        // STARTUP'S GMAIL <--- passed in StartupController, emailService.sendSimpleMessage("from", "to", "subject", "text);
        // But for test purpose, we assign our GMAIL adress.
        message.setFrom(String.valueOf("dumitruantoniodaniel03@gmail.com"));
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}