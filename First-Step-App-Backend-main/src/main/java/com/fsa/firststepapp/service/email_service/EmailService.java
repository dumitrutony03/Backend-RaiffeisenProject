//package com.fsa.firststepapp.service.email_service;
//
//import jakarta.mail.MessagingException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//import java.io.UnsupportedEncodingException;
//
//@Service
//public class EmailService {
//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    public void sendEmail(String from, String to, String subject, String text) {
//        if (!isValidEmail(to)) {
//            throw new IllegalArgumentException("Adresa de e-mail invalidă: " + to);
//        }
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(from);
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//
//        System.out.println("INCERCAM SA TRIMITEM UN EMAIL folosind javaMailSender: ");
//
//        try{
//            javaMailSender.send(message);
//        }catch (Exception ex){
//            System.out.println(ex.getMessage());
//        }
//
//        System.out.println("Email sent successfully.");
//
//        System.out.println("mail trimis folosind javaMailSender: ");
//    }
//
//    private boolean isValidEmail(String email) {
//        return email != null && email.contains("@");
//    }
//}
