package com.example.HostelManagementSystem_Backend.dto.impl;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EquipmentCreateDto {

    private String type;
    private String name;
    private String roomId;
}
