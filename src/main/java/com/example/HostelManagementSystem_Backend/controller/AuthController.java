package com.example.HostelManagementSystem_Backend.controller;

import com.example.HostelManagementSystem_Backend.dto.impl.AuthResponseDto;
import com.example.HostelManagementSystem_Backend.dto.impl.OwnerCreateDto;
import com.example.HostelManagementSystem_Backend.dto.impl.UserSignUpDto;
import com.example.HostelManagementSystem_Backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup/owner")
    public ResponseEntity<AuthResponseDto> registerOwner(@RequestBody OwnerCreateDto ownerCreateDto) {
        return new ResponseEntity<>(authService.registerOwner(ownerCreateDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody UserSignUpDto userSignUpDto) {
        return ResponseEntity.ok(authService.login(userSignUpDto));
    }
}