package com.example.HostelManagementSystem_Backend.service;

import com.example.HostelManagementSystem_Backend.dto.impl.RoomCreateDto;
import com.example.HostelManagementSystem_Backend.dto.impl.RoomResponseDto;

public interface RoomService {
    RoomResponseDto saveRoom(RoomCreateDto dto);
}
