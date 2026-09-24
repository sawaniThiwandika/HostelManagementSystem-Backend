package com.example.HostelManagementSystem_Backend.service.impl;

import com.example.HostelManagementSystem_Backend.dto.impl.RoomCreateDto;
import com.example.HostelManagementSystem_Backend.dto.impl.RoomResponseDto;
import com.example.HostelManagementSystem_Backend.dto.impl.RoomSummaryDto;
import com.example.HostelManagementSystem_Backend.entity.impl.RoomEntity;
import com.example.HostelManagementSystem_Backend.repository.RoomRepository;
import com.example.HostelManagementSystem_Backend.service.RoomService;
import com.example.HostelManagementSystem_Backend.util.IdGenerator;
import com.example.HostelManagementSystem_Backend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final Mapping mapping;
    private final IdGenerator idGenerator;
    @Override
    public RoomResponseDto saveRoom(RoomCreateDto dto) {
        RoomEntity roomEntity = mapping.toRoomEntity(dto);
        roomEntity.setRoomId(idGenerator.generateRoomId());

        RoomEntity savedEntity = roomRepository.save(roomEntity);
        return mapping.toRoomResponseDto(savedEntity);
    }

    @Override
    public List<RoomSummaryDto> getAllRooms() {
        List<RoomEntity> roomEntities = roomRepository.findAll();
        return roomEntities.stream()
                .map(mapping::toRoomSummaryDto) // Uses your Mapping component method
                .collect(Collectors.toList());
    }
}
