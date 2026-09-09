package com.example.HostelManagementSystem_Backend.service;

import com.example.HostelManagementSystem_Backend.dto.impl.OwnerResponseDto;
import java.util.List;

public interface OwnerService {
    OwnerResponseDto getOwnerById(String ownerId);
    List<OwnerResponseDto> getAllOwners();
}