/*
package com.example.HostelManagementSystem_Backend.config;

import com.example.HostelManagementSystem_Backend.dto.impl.*;
import com.example.HostelManagementSystem_Backend.entity.impl.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // RoomEntity > RoomResponseDto
        PropertyMap<RoomEntity, RoomResponseDto> roomToDtoMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setHostelId(source.getHostel().getHostelId());
                map().setHostelName(source.getHostel().getName());
            }
        };

        // StudentEntity > StudentResponseDto
        PropertyMap<StudentEntity, StudentResponseDto> studentToDtoMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setParentId(source.getParent().getParentId());
                map().setParentName(source.getParent().getParentName());
                map().setBedId(source.getBed().getBedId());
                map().setRoomId(source.getBed().getRoom().getRoomId());
            }
        };

        // BedEntity > BedResponseDto
        PropertyMap<BedEntity, BedResponseDto> bedToDtoMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setRoomId(source.getRoom().getRoomId());
                map().setRoomNo(source.getRoom().getNum());
                map().setStudentId(source.getStudent().getStudentId());
                map().setStudentName(source.getStudent().getName());
            }
        };
        // StaffEntity > StaffResponseDto
        PropertyMap<StaffEntity, StaffResponseDto> staffToDtoMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setHostelId(source.getHostel().getHostelId());
                map().setHostelName(source.getHostel().getName());
            }
        };

        // EquipmentEntity > EquipmentResponseDto
        PropertyMap<EquipmentEntity, EquipmentResponseDto> equipmentToDtoMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setRoomId(source.getRoom().getRoomId());
            }
        };

        // PaymentEntity > PaymentResponseDto Map
        PropertyMap<PaymentEntity, PaymentResponseDto> paymentToDtoMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setStudentId(source.getStudent().getStudentId());
                map().setStudentName(source.getStudent().getName());
                map().setStaffId(source.getStaff().getStaffId());
                map().setStaffName(source.getStaff().getStaffName());
            }
        };

        // Register custom property maps
        modelMapper.addMappings(roomToDtoMap);
        modelMapper.addMappings(bedToDtoMap);
        modelMapper.addMappings(staffToDtoMap);
        modelMapper.addMappings(studentToDtoMap);
        modelMapper.addMappings(equipmentToDtoMap);
        modelMapper.addMappings(paymentToDtoMap);

        return modelMapper;
    }

}*/

package com.example.HostelManagementSystem_Backend.config;

import com.example.HostelManagementSystem_Backend.dto.impl.*;
import com.example.HostelManagementSystem_Backend.entity.impl.*;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.StreamWriteConstraints;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ApplicationConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        JsonFactory factory = JsonFactory.builder()
                .streamWriteConstraints(
                        StreamWriteConstraints.builder()
                                .maxNestingDepth(2000)
                                .build()
                )
                .build();
        return new ObjectMapper(factory);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // RoomEntity > RoomResponseDto
        PropertyMap<RoomEntity, RoomResponseDto> roomToDtoMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setHostelId(source.getHostel().getHostelId());
                map().setHostelName(source.getHostel().getName());
            }
        };

        // StudentEntity > StudentResponseDto
        PropertyMap<StudentEntity, StudentResponseDto> studentToDtoMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setParentId(source.getParent().getParentId());
                map().setParentName(source.getParent().getParentName());
                map().setBedId(source.getBed().getBedId());
                map().setRoomId(source.getBed().getRoom().getRoomId());
            }
        };

        // BedEntity > BedResponseDto
        PropertyMap<BedEntity, BedResponseDto> bedToDtoMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setRoomId(source.getRoom().getRoomId());
                map().setRoomNo(source.getRoom().getNum());
                map().setStudentId(source.getStudent().getStudentId());
                map().setStudentName(source.getStudent().getName());
            }
        };
        // StaffEntity > StaffResponseDto
        PropertyMap<StaffEntity, StaffResponseDto> staffToDtoMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setHostelId(source.getHostel().getHostelId());
                map().setHostelName(source.getHostel().getName());
            }
        };

        // EquipmentEntity > EquipmentResponseDto
        PropertyMap<EquipmentEntity, EquipmentResponseDto> equipmentToDtoMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setRoomId(source.getRoom().getRoomId());
            }
        };

        // PaymentEntity > PaymentResponseDto Map
        PropertyMap<PaymentEntity, PaymentResponseDto> paymentToDtoMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setStudentId(source.getStudent().getStudentId());
                map().setStudentName(source.getStudent().getName());
                map().setStaffId(source.getStaff().getStaffId());
                map().setStaffName(source.getStaff().getStaffName());
            }
        };

        // Register custom property maps
        modelMapper.addMappings(roomToDtoMap);
        modelMapper.addMappings(bedToDtoMap);
        modelMapper.addMappings(staffToDtoMap);
        modelMapper.addMappings(studentToDtoMap);
        modelMapper.addMappings(equipmentToDtoMap);
        modelMapper.addMappings(paymentToDtoMap);

        return modelMapper;
    }
}