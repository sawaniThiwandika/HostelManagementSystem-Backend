package com.example.HostelManagementSystem_Backend.config;

import com.example.HostelManagementSystem_Backend.dto.impl.BedResponseDto;
import com.example.HostelManagementSystem_Backend.dto.impl.HostelResponseDto;
import com.example.HostelManagementSystem_Backend.dto.impl.RoomResponseDto;
import com.example.HostelManagementSystem_Backend.dto.impl.StudentResponseDto;
import com.example.HostelManagementSystem_Backend.entity.impl.BedEntity;
import com.example.HostelManagementSystem_Backend.entity.impl.HostelEntity;
import com.example.HostelManagementSystem_Backend.entity.impl.RoomEntity;
import com.example.HostelManagementSystem_Backend.entity.impl.StudentEntity;
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

        // BedEntity to BedResponseDto
        PropertyMap<BedEntity, BedResponseDto> bedToDtoMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setRoomId(source.getRoom().getRoomId());
                map().setRoomNo(source.getRoom().getNum());
            }
        };
        PropertyMap<StudentEntity, StudentResponseDto> studentToDtoMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setParentId(source.getParent().getParentId());
                map().setParentName(source.getParent().getParentName());
            }
        };

        // Register custom property maps
        modelMapper.addMappings(roomToDtoMap);
        modelMapper.addMappings(bedToDtoMap);
        modelMapper.addMappings(studentToDtoMap);

        return modelMapper;
    }

}