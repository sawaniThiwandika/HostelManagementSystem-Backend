package com.example.HostelManagementSystem_Backend.dto.impl;

import com.example.HostelManagementSystem_Backend.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponseDto implements SuperDto {
    private String roomId;
    private Integer num;
    private Integer floor;
    private String category;
    private Integer size;
    private String description;
    private Double fee;
    private String hostelId;
    private String hostelName;
}
