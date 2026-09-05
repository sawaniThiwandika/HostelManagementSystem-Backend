package com.example.HostelManagementSystem_Backend.util;


import com.example.HostelManagementSystem_Backend.dto.impl.*;
import com.example.HostelManagementSystem_Backend.entity.impl.*;
import com.example.HostelManagementSystem_Backend.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
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
    private final BedRepository bedRepository;

    public Mapping(ModelMapper modelMapper,
                   RoomRepository roomRepository,
                   HostelRepository hostelRepository,
                   OwnerRepository ownerRepository,
                   ParentRepository parentRepository,
                   StudentRepository studentRepository,
                   BedRepository bedRepository) {
        this.modelMapper = modelMapper;
        this.roomRepository = roomRepository;
        this.hostelRepository = hostelRepository;
        this.ownerRepository = ownerRepository;
        this.parentRepository = parentRepository;
        this.studentRepository = studentRepository;
        this.bedRepository = bedRepository;
    }

    // Bed Mappings

    public BedEntity toBedEntity(BedCreateRequestDto dto) {
        BedEntity bedEntity = modelMapper.map(dto, BedEntity.class);

        if (dto.getRoomId() != 0) {
            RoomEntity roomEntity = roomRepository.findById(String.valueOf(dto.getRoomId()))
                    .orElseThrow(() -> new RuntimeException("Room not found with ID: " + dto.getRoomId()));
            bedEntity.setRoom(roomEntity);
        }

        return bedEntity;
    }

    public BedResponseDto toBedResponseDto(BedEntity entity) {
        BedResponseDto dto = modelMapper.map(entity, BedResponseDto.class);

        // Derived dynamically, true if no student assigned, false if occupied
        dto.setAvailability(entity.getStudent() == null);

        if (entity.getStudent() != null) {
            dto.setStudentId(entity.getStudent().getStudentId());
            dto.setStudentName(entity.getStudent().getName());
        }

        return dto;
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

        String fullAddress = AddressUtils.formatAddress(
                dto.getStreetAddress(),
                dto.getCity(),
                dto.getProvince(),
                dto.getPostelCode()
        );
        hostelEntity.setAddress(fullAddress);

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

        // Map parent
        if (dto.getParentId() != null) {
            ParentEntity parent = parentRepository.findById(dto.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent not found with ID: " + dto.getParentId()));
            studentEntity.setParent(parent);
        }

        if (dto.getBedId() != null) {
            BedEntity bed = bedRepository.findById(dto.getBedId())
                    .orElseThrow(() -> new RuntimeException("Bed not found with ID: " + dto.getBedId()));

            if (bed.getStudent() != null) {
                throw new IllegalStateException("Bed (" + dto.getBedId() + ") is already assigned to another student.");
            }
            studentEntity.setBed(bed);
        } else {
            throw new IllegalArgumentException("Bed ID is mandatory for student registration.");
        }

        return studentEntity;
    }

    public StudentResponseDto toStudentResponseDto(StudentEntity entity) {
        StudentResponseDto dto = modelMapper.map(entity, StudentResponseDto.class);

        if (entity.getParent() != null) {
            dto.setParentId(entity.getParent().getParentId());
            dto.setParentName(entity.getParent().getParentName());
        }

        if (entity.getBed() != null) {
            dto.setBedId(entity.getBed().getBedId());
            if (entity.getBed().getRoom() != null) {
                dto.setRoomId(entity.getBed().getRoom().getRoomId());
            }
        }

        return dto;
    }

    public StudentSummaryDto toStudentSummaryDto(StudentEntity entity) {
        return modelMapper.map(entity, StudentSummaryDto.class);
    }

    public List<StudentResponseDto> toStudentResponseDtoList(List<StudentEntity> studentEntities) {
        return studentEntities.stream()
                .map(this::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    // Staff Mapping

    public StaffEntity toStaffEntity(StaffCreateDto dto) {
        StaffEntity staffEntity = modelMapper.map(dto, StaffEntity.class);

        if (dto.getHostelId() != null) {
            HostelEntity hostel = hostelRepository.findById(dto.getHostelId())
                    .orElseThrow(() -> new RuntimeException("Hostel not found with ID: " + dto.getHostelId()));
            staffEntity.setHostel(hostel);
        }

        return staffEntity;
    }

    public StaffResponseDto toStaffResponseDto(StaffEntity entity) {
        return modelMapper.map(entity, StaffResponseDto.class);
    }

    public List<StaffResponseDto> toStaffResponseDtoList(List<StaffEntity> staffEntities) {
        return staffEntities.stream()
                .map(this::toStaffResponseDto)
                .collect(Collectors.toList());
    }

    // Complaint Mapping

    public ComplaintEntity toComplaintEntity(ComplaintCreateDto dto, StudentEntity student) {
        ComplaintEntity complaintEntity = modelMapper.map(dto, ComplaintEntity.class);

        complaintEntity.setDate(LocalDate.now());
        complaintEntity.setStudent(student);
        complaintEntity.setPriority(null); // Warden will assign priority later


        if (student.getBed() != null && student.getBed().getRoom() != null) {
            complaintEntity.setRoom(student.getBed().getRoom());
        } else {
            throw new IllegalStateException(
                    "Student (" + student.getStudentId() + ") does not have a valid room linked to their assigned bed."
            );
        }

        return complaintEntity;
    }

    public ComplaintResponseDto toComplaintResponseDto(ComplaintEntity entity) {
        ComplaintResponseDto dto = modelMapper.map(entity, ComplaintResponseDto.class);

        if (entity.getStudent() != null) {
            dto.setStudentId(entity.getStudent().getStudentId());
            dto.setStudentName(entity.getStudent().getName());
        }

        if (entity.getRoom() != null) {
            dto.setRoomId(entity.getRoom().getRoomId());
        }

        return dto;
    }

    public List<ComplaintResponseDto> toComplaintResponseDtoList(List<ComplaintEntity> complaintEntities) {
        return complaintEntities.stream()
                .map(this::toComplaintResponseDto)
                .collect(Collectors.toList());
    }

    //Equipment Mapping
    public EquipmentEntity toEquipmentEntity(EquipmentCreateDto dto) {
        EquipmentEntity equipmentEntity = modelMapper.map(dto, EquipmentEntity.class);

        if (dto.getRoomId() != null && !dto.getRoomId().isBlank()) {
            RoomEntity roomEntity = roomRepository.findById(dto.getRoomId())
                    .orElseThrow(() -> new RuntimeException("Room not found with ID: " + dto.getRoomId()));
            equipmentEntity.setRoom(roomEntity);
        }

        return equipmentEntity;
    }

    public EquipmentResponseDto toEquipmentResponseDto(EquipmentEntity entity) {
        EquipmentResponseDto dto = modelMapper.map(entity, EquipmentResponseDto.class);

        if (entity.getRoom() != null) {
            dto.setRoomId(entity.getRoom().getRoomId());
        }

        return dto;
    }

    public List<EquipmentResponseDto> toEquipmentResponseDtoList(List<EquipmentEntity> equipmentEntities) {
        return equipmentEntities.stream()
                .map(this::toEquipmentResponseDto)
                .collect(Collectors.toList());
    }

    public PaymentEntity toPaymentEntity(PaymentCreateDto dto) {
        PaymentEntity paymentEntity = modelMapper.map(dto, PaymentEntity.class);

        paymentEntity.setDate(LocalDate.now());
        paymentEntity.setTime(LocalTime.now());

        if (dto.getStudentId() != null && !dto.getStudentId().isBlank()) {
            StudentEntity studentEntity = studentRepository.findById(dto.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student not found with ID: " + dto.getStudentId()));
            paymentEntity.setStudent(studentEntity);
        }

        return paymentEntity;
    }

    public PaymentResponseDto toPaymentResponseDto(PaymentEntity entity) {
        PaymentResponseDto dto = modelMapper.map(entity, PaymentResponseDto.class);

        if (entity.getStudent() != null) {
            dto.setStudentId(entity.getStudent().getStudentId());
            dto.setStudentName(entity.getStudent().getName());
        }

        if (entity.getStaff() != null) {
            dto.setStaffId(entity.getStaff().getStaffId());
            dto.setStaffName(entity.getStaff().getStaffName());
        }

        return dto;
    }

    public List<PaymentResponseDto> toPaymentResponseDtoList(List<PaymentEntity> paymentEntities) {
        return paymentEntities.stream()
                .map(this::toPaymentResponseDto)
                .collect(Collectors.toList());
    }
}