package com.example.HostelManagementSystem_Backend.service;


import com.example.HostelManagementSystem_Backend.dto.impl.*;

public interface AuthService {
    AuthResponseDto registerOwner(OwnerCreateDto ownerCreateDto);
    AuthResponseDto login(UserSignUpDto userSignUpDto);
    TokenRefreshResponseDto refreshToken(TokenRefreshRequestDto request);
}
