package com.example.HostelManagementSystem_Backend.service.impl;

import com.example.HostelManagementSystem_Backend.dto.impl.OwnerResponseDto;
import com.example.HostelManagementSystem_Backend.entity.impl.OwnerEntity;
import com.example.HostelManagementSystem_Backend.repository.OwnerRepository;
import com.example.HostelManagementSystem_Backend.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Override
    public OwnerResponseDto getOwnerById(String ownerId) {
        OwnerEntity owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found with ID: " + ownerId));
        return mapToOwnerResponseDto(owner);
    }

    @Override
    public List<OwnerResponseDto> getAllOwners() {
        return ownerRepository.findAll().stream()
                .map(this::mapToOwnerResponseDto)
                .toList();
    }

    private OwnerResponseDto mapToOwnerResponseDto(OwnerEntity owner) {
        OwnerResponseDto dto = new OwnerResponseDto();
        dto.setOwnerId(owner.getOwnerId());
        dto.setOwnerName(owner.getOwnerName());
        dto.setNic(owner.getNic());
        dto.setTel(owner.getTel());
        dto.setAddress(owner.getAddress());
        dto.setWorkplace(owner.getWorkplace());
        dto.setJobTitle(owner.getJobTitle());
        dto.setEmail(owner.getEmail());
        return dto;
    }
}