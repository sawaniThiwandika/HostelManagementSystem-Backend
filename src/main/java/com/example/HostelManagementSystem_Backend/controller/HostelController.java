package com.example.HostelManagementSystem_Backend.controller;

import com.example.HostelManagementSystem_Backend.dto.impl.HostelCreateDto;
import com.example.HostelManagementSystem_Backend.entity.impl.HostelEntity;
import com.example.HostelManagementSystem_Backend.service.impl.HostelServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hostels")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class HostelController {

    private final HostelServiceImpl hostelService;

    @PostMapping
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<HostelEntity> createHostel(
            @Valid @RequestBody HostelCreateDto hostelCreateDto,
            @AuthenticationPrincipal UserDetails userDetails) {

        HostelEntity savedHostel = hostelService.createHostel(hostelCreateDto, userDetails.getUsername());
        return new ResponseEntity<>(savedHostel, HttpStatus.CREATED);
    }
}