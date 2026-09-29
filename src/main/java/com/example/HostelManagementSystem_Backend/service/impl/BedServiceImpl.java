package com.example.HostelManagementSystem_Backend.service.impl;

import com.example.HostelManagementSystem_Backend.dto.impl.BedCreateRequestDto;
import com.example.HostelManagementSystem_Backend.dto.impl.BedResponseDto;
import com.example.HostelManagementSystem_Backend.dto.impl.RoomCreateDto;
import com.example.HostelManagementSystem_Backend.entity.impl.BedEntity;
import com.example.HostelManagementSystem_Backend.repository.BedRepository;
import com.example.HostelManagementSystem_Backend.service.BedService;
import com.example.HostelManagementSystem_Backend.util.IdGenerator;
import com.example.HostelManagementSystem_Backend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



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
    public List<BedResponseDto> getAllRBed() {
        return List.of();
    }

    @Override
    public BedResponseDto getBedById(String id) {
        return null;
    }

    @Override
    public BedResponseDto updateBed(String id, RoomCreateDto dto) {
        return null;
    }

    @Override
    public void deleteBed(String id) {

    }
}
