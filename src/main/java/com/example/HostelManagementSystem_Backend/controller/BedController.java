package com.example.HostelManagementSystem_Backend.controller;

import com.example.HostelManagementSystem_Backend.dto.impl.BedCreateRequestDto;
import com.example.HostelManagementSystem_Backend.dto.impl.BedResponseDto;
import com.example.HostelManagementSystem_Backend.service.BedService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/beds")
@RequiredArgsConstructor
public class BedController {

    private final BedService bedService;

    @PostMapping
    @PreAuthorize("hasRole('OWNER') or hasRole('WARDEN')")
    public ResponseEntity<BedResponseDto> saveBed(@Valid @RequestBody BedCreateRequestDto bedCreateRequestDto) {
        BedResponseDto savedBed = bedService.saveBed(bedCreateRequestDto);
        return new ResponseEntity<>(savedBed, HttpStatus.CREATED);
    }
}