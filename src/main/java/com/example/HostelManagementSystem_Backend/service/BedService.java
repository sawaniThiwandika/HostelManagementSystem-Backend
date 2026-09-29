package com.example.HostelManagementSystem_Backend.service;

import com.example.HostelManagementSystem_Backend.dto.impl.BedCreateRequestDto;
import com.example.HostelManagementSystem_Backend.dto.impl.BedResponseDto;
import com.example.HostelManagementSystem_Backend.dto.impl.RoomCreateDto;


import java.util.List;

public interface BedService {
    public BedResponseDto saveBed(BedCreateRequestDto dto);
    List<BedResponseDto> getAllRBed();
    BedResponseDto getBedById(String id);
    BedResponseDto updateBed(String id, RoomCreateDto dto);
    void deleteBed(String id);
}
