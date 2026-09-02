package com.example.HostelManagementSystem_Backend.dto.impl;


import com.example.HostelManagementSystem_Backend.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomSummaryDto implements SuperDto {

    private String roomId;
    private Integer num;
    private Integer floor;
    private String category;
    private Double fee;
}