package com.example.HostelManagementSystem_Backend.service.impl;

import com.example.HostelManagementSystem_Backend.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public void sendStaffCredentials(String toEmail, String staffName, String username, String temporaryPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Welcome to Hostel Management System - Your Staff Credentials");
        message.setText(String.format(
                "Dear %s,\n\nYour staff account has been created successfully.\n\n" +
                        "Here are your login credentials:\n" +
                        "Username: %s\n" +
                        "Temporary Password: %s\n\n" +
                        "Please log in and update your password.\n\nBest Regards,\nHostel Administration",
                staffName, username, temporaryPassword
        ));

        mailSender.send(message);
    }
}
