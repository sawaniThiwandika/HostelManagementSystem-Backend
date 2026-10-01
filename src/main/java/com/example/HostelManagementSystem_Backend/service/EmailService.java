package com.example.HostelManagementSystem_Backend.service;

public interface EmailService {
    public void sendStaffCredentials(String toEmail, String staffName, String username, String temporaryPassword) ;
}
