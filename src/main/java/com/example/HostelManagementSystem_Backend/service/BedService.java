package com.example.HostelManagementSystem_Backend.service;

import com.example.HostelManagementSystem_Backend.dto.impl.BedCreateRequestDto;
import com.example.HostelManagementSystem_Backend.dto.impl.BedResponseDto;
import com.example.HostelManagementSystem_Backend.dto.impl.RoomCreateDto;


import java.util.List;

public interface BedService {
    public BedResponseDto saveBed(BedCreateRequestDto dto);
    List<BedResponseDto> getAllBeds();
    BedResponseDto getBedById(String id);
    public BedResponseDto updateBed(String id, BedCreateRequestDto dto);
    void deleteBed(String id);
}
