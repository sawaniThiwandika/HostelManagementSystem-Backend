package com.example.HostelManagementSystem_Backend.controller;

import com.example.HostelManagementSystem_Backend.dto.impl.StaffCreateDto;
import com.example.HostelManagementSystem_Backend.dto.impl.StaffResponseDto;
import com.example.HostelManagementSystem_Backend.repository.StaffRepository;
import com.example.HostelManagementSystem_Backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/staff")
@RequiredArgsConstructor
public class StaffController {
    private final AuthService authService;
    @PostMapping("/register")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<String> registerStaff(@Valid @RequestBody StaffCreateDto staffCreateDto) {
        String response = authService.registerStaff(staffCreateDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
