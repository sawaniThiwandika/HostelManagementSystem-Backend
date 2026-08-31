package com.example.HostelManagementSystem_Backend.dto.impl;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BedCreateRequestDto {

    private String type;
    private String description;
    private int roomId;

}
