package com.example.HostelManagementSystem_Backend.controller;

import com.example.HostelManagementSystem_Backend.dto.impl.RoomCreateDto;
import com.example.HostelManagementSystem_Backend.dto.impl.RoomResponseDto;
import com.example.HostelManagementSystem_Backend.dto.impl.RoomSummaryDto;
import com.example.HostelManagementSystem_Backend.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<RoomResponseDto> saveRoom(@Valid @RequestBody RoomCreateDto roomCreateDto) {
        RoomResponseDto savedRoom = roomService.saveRoom(roomCreateDto);
        return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoomSummaryDto>> getAllRooms() {
        List<RoomSummaryDto> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<RoomResponseDto> getRoomById(@PathVariable String id) {
        RoomResponseDto room = roomService.getRoomById(id);
        return ResponseEntity.ok(room);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponseDto> updateRoom(
            @PathVariable String id,
            @Valid @RequestBody RoomCreateDto roomCreateDto) {
        RoomResponseDto updatedRoom = roomService.updateRoom(id, roomCreateDto);
        return ResponseEntity.ok(updatedRoom);
    }

}
