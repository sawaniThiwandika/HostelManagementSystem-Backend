package com.example.HostelManagementSystem_Backend.service.impl;

import com.example.HostelManagementSystem_Backend.dto.impl.BedCreateRequestDto;
import com.example.HostelManagementSystem_Backend.dto.impl.BedResponseDto;
import com.example.HostelManagementSystem_Backend.dto.impl.RoomCreateDto;
import com.example.HostelManagementSystem_Backend.entity.impl.BedEntity;
import com.example.HostelManagementSystem_Backend.entity.impl.RoomEntity;
import com.example.HostelManagementSystem_Backend.repository.BedRepository;
import com.example.HostelManagementSystem_Backend.service.BedService;
import com.example.HostelManagementSystem_Backend.util.IdGenerator;
import com.example.HostelManagementSystem_Backend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class BedServiceImpl implements BedService {

    private final Mapping mapping;
    private final BedRepository bedRepository;
    private final IdGenerator idGenerator;

    @Override
    public BedResponseDto saveBed(BedCreateRequestDto dto) {
        BedEntity bedEntity = mapping.toBedEntity(dto);
        bedEntity.setBedId(idGenerator.generateBedId());

        BedEntity savedEntity = bedRepository.save(bedEntity);
        return mapping.toBedResponseDto(savedEntity);
    }

    @Override
    public List<BedResponseDto> getAllBeds() {
        List<BedEntity> bedEntities = bedRepository.findAll();
        return bedEntities.stream()
                .map(mapping::toBedResponseDto)
                .collect(Collectors.toList());

    }

    @Override
    public BedResponseDto getBedById(String id) {
        BedEntity bedEntity = bedRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bed not found with ID: " + id));
        return mapping.toBedResponseDto(bedEntity);
    }

    @Override
    public BedResponseDto updateBed(String id, BedCreateRequestDto dto) {
        if (!bedRepository.existsById(id)) {
            throw new RuntimeException("Bed not found with ID: " + id);
        }

        BedEntity bedEntity = mapping.toBedEntity(dto);
        bedEntity.setBedId(id);

        BedEntity updatedEntity = bedRepository.save(bedEntity);
        return mapping.toBedResponseDto(updatedEntity);
    }

    @Override
    public void deleteBed(String id) {

    }
}
