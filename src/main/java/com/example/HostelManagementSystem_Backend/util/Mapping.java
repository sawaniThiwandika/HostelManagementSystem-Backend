package com.example.HostelManagementSystem_Backend.util;

import com.example.HostelManagementSystem_Backend.dto.impl.*;
import com.example.HostelManagementSystem_Backend.entity.impl.*;
import com.example.HostelManagementSystem_Backend.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapping {

    private final ModelMapper modelMapper;
    private final RoomRepository roomRepository;
    private final HostelRepository hostelRepository;
    private final OwnerRepository ownerRepository;
    private final ParentRepository parentRepository;
    private final StudentRepository studentRepository;

    public Mapping(ModelMapper modelMapper,
                   RoomRepository roomRepository,
                   HostelRepository hostelRepository,
                   OwnerRepository ownerRepository,
                   ParentRepository parentRepository,
                   StudentRepository studentRepository) {
        this.modelMapper = modelMapper;
        this.roomRepository = roomRepository;
        this.hostelRepository = hostelRepository;
        this.ownerRepository = ownerRepository;
        this.parentRepository = parentRepository;
        this.studentRepository = studentRepository;

    }

    // Bed Mappings


    public BedEntity toBedEntity(BedCreateRequestDto dto) {
        BedEntity bedEntity = modelMapper.map(dto, BedEntity.class);
        bedEntity.setAvailability(true);

        if (dto.getRoomId() != 0) {
            RoomEntity roomEntity = roomRepository.findById(String.valueOf(dto.getRoomId()))
                    .orElseThrow(() -> new RuntimeException("Room not found with ID: " + dto.getRoomId()));
            bedEntity.setRoom(roomEntity);
        }

        return bedEntity;
    }

    public BedResponseDto toBedResponseDto(BedEntity entity) {
        return modelMapper.map(entity, BedResponseDto.class);
    }

    public List<BedResponseDto> toBedResponseDtoList(List<BedEntity> bedEntities) {
        return bedEntities.stream()
                .map(this::toBedResponseDto)
                .collect(Collectors.toList());
    }

    // Room Mappings

    public RoomEntity toRoomEntity(RoomCreateDto dto) {
        RoomEntity roomEntity = modelMapper.map(dto, RoomEntity.class);

        if (dto.getHostelId() != null) {
            HostelEntity hostel = hostelRepository.findById(dto.getHostelId())
                    .orElseThrow(() -> new RuntimeException("Hostel not found with ID: " + dto.getHostelId()));
            roomEntity.setHostel(hostel);
        }

        return roomEntity;
    }

    public RoomResponseDto toRoomResponseDto(RoomEntity entity) {
        return modelMapper.map(entity, RoomResponseDto.class);
    }

    public RoomSummaryDto toRoomSummaryDto(RoomEntity entity) {
        return modelMapper.map(entity, RoomSummaryDto.class);
    }

    public List<RoomResponseDto> toRoomResponseDtoList(List<RoomEntity> roomEntities) {
        return roomEntities.stream()
                .map(this::toRoomResponseDto)
                .collect(Collectors.toList());
    }


    // Hostel Mappings
    public HostelEntity toHostelEntity(HostelCreateDto dto) {
        HostelEntity hostelEntity = modelMapper.map(dto, HostelEntity.class);

        // Concatenate address fields
        String fullAddress = AddressUtils.formatAddress(
                dto.getStreetAddress(),
                dto.getCity(),
                dto.getProvince(),
                dto.getPostelCode()
        );
        hostelEntity.setAddress(fullAddress);

        // Link OwnerEntity
        if (dto.getOwnerId() != null) {
            OwnerEntity owner = ownerRepository.findById(dto.getOwnerId())
                    .orElseThrow(() -> new RuntimeException("Owner not found with ID: " + dto.getOwnerId()));
            hostelEntity.setOwner(owner);
        }

        return hostelEntity;
    }

    public HostelResponseDto toHostelResponseDto(HostelEntity entity) {
        return modelMapper.map(entity, HostelResponseDto.class);
    }

    public HostelSummaryDto toHostelSummaryDto(HostelEntity entity) {
        return modelMapper.map(entity, HostelSummaryDto.class);
    }

    public List<HostelResponseDto> toHostelResponseDtoList(List<HostelEntity> hostelEntities) {
        return hostelEntities.stream()
                .map(this::toHostelResponseDto)
                .collect(Collectors.toList());
    }


    // Owner Mappings

    public OwnerEntity toOwnerEntity(OwnerResponseDto dto) {
        return modelMapper.map(dto, OwnerEntity.class);
    }

    public OwnerResponseDto toOwnerResponseDto(OwnerEntity entity) {
        return modelMapper.map(entity, OwnerResponseDto.class);
    }

    public OwnerSummeryDto toOwnerSummaryDto(OwnerEntity entity) {
        return modelMapper.map(entity, OwnerSummeryDto.class);
    }

    public List<OwnerResponseDto> toOwnerResponseDtoList(List<OwnerEntity> ownerEntities) {
        return ownerEntities.stream()
                .map(this::toOwnerResponseDto)
                .collect(Collectors.toList());
    }


    // Parent Mappings


    public ParentEntity toParentEntity(ParentCreateDto dto) {
        ParentEntity parentEntity = modelMapper.map(dto, ParentEntity.class);

        String fullAddress = AddressUtils.formatAddress(
                dto.getStreetAddress(),
                dto.getCity(),
                dto.getProvince(),
                dto.getPostelCode()
        );
        parentEntity.setParentAddress(fullAddress);

        return parentEntity;
    }

    public ParentResponseDto toParentResponseDto(ParentEntity entity) {
        return modelMapper.map(entity, ParentResponseDto.class);
    }


    public List<ParentResponseDto> toParentResponseDtoList(List<ParentEntity> parentEntities) {
        return parentEntities.stream()
                .map(this::toParentResponseDto)
                .collect(Collectors.toList());
    }


    // Student Mappings


    public StudentEntity toStudentEntity(StudentCreateDto dto) {
        StudentEntity studentEntity = modelMapper.map(dto, StudentEntity.class);

        String fullAddress = AddressUtils.formatAddress(
                dto.getStreetAddress(),
                dto.getCity(),
                dto.getProvince(),
                dto.getPostalCode()
        );
        studentEntity.setAddress(fullAddress);

        // map parent
        if (dto.getParentId() != null) {
            ParentEntity parent = parentRepository.findById(dto.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent not found with ID: " + dto.getParentId()));
            studentEntity.setParent(parent);
        }

        return studentEntity;
    }

    public StudentResponseDto toStudentResponseDto(StudentEntity entity) {
        return modelMapper.map(entity, StudentResponseDto.class);
    }

    public StudentSummaryDto toStudentSummaryDto(StudentEntity entity) {
        return modelMapper.map(entity, StudentSummaryDto.class);
    }

    public List<StudentResponseDto> toStudentResponseDtoList(List<StudentEntity> studentEntities) {
        return studentEntities.stream()
                .map(this::toStudentResponseDto)
                .collect(Collectors.toList());
    }
}