package com.example.HostelManagementSystem_Backend.service;

import com.example.HostelManagementSystem_Backend.dto.impl.RoomCreateDto;
import com.example.HostelManagementSystem_Backend.dto.impl.RoomResponseDto;
import com.example.HostelManagementSystem_Backend.dto.impl.RoomSummaryDto;

import java.util.List;

public interface RoomService {
    RoomResponseDto saveRoom(RoomCreateDto dto);
    List<RoomSummaryDto> getAllRooms();
}
