package com.example.HostelManagementSystem_Backend.controller;

import com.example.HostelManagementSystem_Backend.dto.impl.OwnerResponseDto;
import com.example.HostelManagementSystem_Backend.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/owners")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_OWNER')")
    public ResponseEntity<OwnerResponseDto> getOwnerById(@PathVariable("id") String ownerId) {
        return ResponseEntity.ok(ownerService.getOwnerById(ownerId));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_OWNER')")
    public ResponseEntity<List<OwnerResponseDto>> getAllOwners() {
        return ResponseEntity.ok(ownerService.getAllOwners());
    }
}