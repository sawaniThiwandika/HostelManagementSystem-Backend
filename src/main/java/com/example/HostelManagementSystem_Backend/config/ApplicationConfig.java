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

        // RoomEntity to RoomResponseDto
        PropertyMap<RoomEntity, RoomResponseDto> roomToDtoMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setHostelId(source.getHostel().getHostelId());
                map().setHostelName(source.getHostel().getName());
            }
        };

        // StudentEntity -> StudentResponseDto
        PropertyMap<StudentEntity, StudentResponseDto> studentToDtoMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setParentId(source.getParent().getParentId());
                map().setParentName(source.getParent().getParentName());
                map().setBedId(source.getBed().getBedId());
                map().setRoomId(source.getBed().getRoom().getRoomId());
            }
        };

        // BedEntity -> BedResponseDto
        PropertyMap<BedEntity, BedResponseDto> bedToDtoMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setRoomId(source.getRoom().getRoomId());
                map().setRoomNo(source.getRoom().getNum());
                map().setStudentId(source.getStudent().getStudentId());
                map().setStudentName(source.getStudent().getName());
            }
        };

        PropertyMap<StaffEntity, StaffResponseDto> staffToDtoMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setHostelId(source.getHostel().getHostelId());
                map().setHostelName(source.getHostel().getName());
            }
        };

        // Register custom property maps
        modelMapper.addMappings(roomToDtoMap);
        modelMapper.addMappings(bedToDtoMap);
        modelMapper.addMappings(staffToDtoMap);
        modelMapper.addMappings(studentToDtoMap);

        return modelMapper;
    }

}