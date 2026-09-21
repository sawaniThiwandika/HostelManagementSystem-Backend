package com.example.HostelManagementSystem_Backend.service;

import com.example.HostelManagementSystem_Backend.dto.impl.HostelCreateDto;
import com.example.HostelManagementSystem_Backend.dto.impl.HostelResponseDto;
import com.example.HostelManagementSystem_Backend.dto.impl.HostelSummaryDto;
import com.example.HostelManagementSystem_Backend.entity.impl.HostelEntity;

import java.util.List;

public interface HostelService {
    public HostelSummaryDto createHostel(HostelCreateDto dto, String username);
    public List<HostelSummaryDto> getAllHostels();
    public HostelResponseDto getHostelById(String id) ;
}


