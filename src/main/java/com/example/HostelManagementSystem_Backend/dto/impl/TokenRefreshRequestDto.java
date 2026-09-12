package com.example.HostelManagementSystem_Backend.dto.impl;

import lombok.Data;

@Data
public class TokenRefreshRequestDto {
    private String refreshToken;
}