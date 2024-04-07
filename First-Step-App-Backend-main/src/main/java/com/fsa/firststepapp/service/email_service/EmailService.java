package com.fsa.firststepapp.service.email_service;

public interface EmailService {
    public void sendSimpleMessage(
            String to, String subject, String text);
}