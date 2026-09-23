package com.example.HostelManagementSystem_Backend.service.impl;

import com.example.HostelManagementSystem_Backend.dto.impl.HostelCreateDto;
import com.example.HostelManagementSystem_Backend.dto.impl.HostelSummaryDto;
import com.example.HostelManagementSystem_Backend.dto.impl.HostelResponseDto;
import com.example.HostelManagementSystem_Backend.entity.impl.HostelEntity;
import com.example.HostelManagementSystem_Backend.entity.impl.OwnerEntity;
import com.example.HostelManagementSystem_Backend.repository.HostelRepository;
import com.example.HostelManagementSystem_Backend.repository.OwnerRepository;
import com.example.HostelManagementSystem_Backend.service.HostelService;
import com.example.HostelManagementSystem_Backend.util.IdGenerator;
import com.example.HostelManagementSystem_Backend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HostelServiceImpl implements HostelService {

    private final HostelRepository hostelRepository;
    private final OwnerRepository ownerRepository;
    private final IdGenerator idGenerator;
    private final Mapping mapping;

    @Transactional
    public HostelSummaryDto createHostel(HostelCreateDto dto, String username) {
        OwnerEntity owner = ownerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Owner not found with username: " + username));

        HostelEntity hostel = mapping.toHostelEntity(dto);
        hostel.setHostelId(idGenerator.generateHostelId());
        hostel.setOwner(owner);

        HostelEntity entity= hostelRepository.save(hostel);
        return  mapping.toHostelSummaryDto(entity, HostelSummaryDto.class);
    }

    public List<HostelSummaryDto> getAllHostels() {
        List<HostelEntity> hostelEntities = hostelRepository.findAll();

        return hostelEntities.stream()
                .map(entity -> mapping.toHostelSummaryDto(entity, HostelSummaryDto.class))
                .collect(Collectors.toList());
    }

    public HostelResponseDto getHostelById(String id) {
        HostelEntity hostelEntity = hostelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hostel not found with id: " + id));

        return mapping.toHostelResponseDto(hostelEntity);
    }

    public HostelResponseDto updateHostel(String id, HostelCreateDto dto) {
        HostelEntity existingHostel = hostelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hostel not found with id: " + id));

        HostelEntity updatedData = mapping.toHostelEntity(dto);

        existingHostel.setName(updatedData.getName());
        existingHostel.setAddress(updatedData.getAddress());
        existingHostel.setTel(updatedData.getTel());
        existingHostel.setEmail(updatedData.getEmail());
        existingHostel.setLocation(updatedData.getLocation());

        HostelEntity savedHostel = hostelRepository.save(existingHostel);
        return mapping.toHostelResponseDto(savedHostel);
    }

    public void deleteHostel(String id) {
        if (!hostelRepository.existsById(id)) {
            throw new RuntimeException("Hostel not found with id: " + id);
        }
        hostelRepository.deleteById(id);
    }
    
}