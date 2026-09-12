package com.example.HostelManagementSystem_Backend.service.impl;

import com.example.HostelManagementSystem_Backend.dto.impl.HostelCreateDto;
import com.example.HostelManagementSystem_Backend.entity.impl.HostelEntity;
import com.example.HostelManagementSystem_Backend.entity.impl.OwnerEntity;
import com.example.HostelManagementSystem_Backend.repository.HostelRepository;
import com.example.HostelManagementSystem_Backend.repository.OwnerRepository;
import com.example.HostelManagementSystem_Backend.util.IdGenerator;
import com.example.HostelManagementSystem_Backend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HostelServiceImpl {

    private final HostelRepository hostelRepository;
    private final OwnerRepository ownerRepository;
    private final IdGenerator idGenerator;
    private final Mapping mapping;

    @Transactional
    public HostelEntity createHostel(HostelCreateDto dto, String username) {
        OwnerEntity owner = ownerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Owner not found with username: " + username));

        HostelEntity hostel = mapping.toHostelEntity(dto);
        hostel.setHostelId(idGenerator.generateHostelId());
        hostel.setOwner(owner);

        return hostelRepository.save(hostel);
    }
}