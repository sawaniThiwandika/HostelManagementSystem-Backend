package com.example.HostelManagementSystem_Backend.service;


import com.example.HostelManagementSystem_Backend.dto.impl.AuthResponseDto;
import com.example.HostelManagementSystem_Backend.dto.impl.OwnerCreateDto;
import com.example.HostelManagementSystem_Backend.dto.impl.UserSignUpDto;

public interface AuthService {
    AuthResponseDto registerOwner(OwnerCreateDto ownerCreateDto);
    AuthResponseDto login(UserSignUpDto userSignUpDto);
}
