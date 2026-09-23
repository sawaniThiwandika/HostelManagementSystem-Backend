package com.example.HostelManagementSystem_Backend.controller;

import com.example.HostelManagementSystem_Backend.dto.impl.HostelCreateDto;
import com.example.HostelManagementSystem_Backend.dto.impl.HostelSummaryDto;
import com.example.HostelManagementSystem_Backend.dto.impl.HostelResponseDto;
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

import java.util.List;

@RestController
@RequestMapping("/api/v1/hostels")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class HostelController {

    private final HostelServiceImpl hostelService;

    @PostMapping
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<HostelSummaryDto> createHostel(
            @Valid @RequestBody HostelCreateDto hostelCreateDto,
            @AuthenticationPrincipal UserDetails userDetails) {

        HostelSummaryDto savedHostel = hostelService.createHostel(hostelCreateDto, userDetails.getUsername());
        return new ResponseEntity<>(savedHostel, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HostelSummaryDto>> getAllHostels() {
        List<HostelSummaryDto> hostels = hostelService.getAllHostels();
        return ResponseEntity.ok(hostels);
    }
    @GetMapping("/{id}")
    public ResponseEntity<HostelResponseDto> getHostelById(@PathVariable String id) {
        HostelResponseDto hostel = hostelService.getHostelById(id);
        return ResponseEntity.ok(hostel);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<HostelResponseDto> updateHostel(
            @PathVariable String id,
            @Valid @RequestBody HostelCreateDto hostelCreateDto) {

        HostelResponseDto updatedHostel = hostelService.updateHostel(id, hostelCreateDto);
        return ResponseEntity.ok(updatedHostel);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<Void> deleteHostel(@PathVariable String id) {
        hostelService.deleteHostel(id);
        return ResponseEntity.noContent().build();
    }



}