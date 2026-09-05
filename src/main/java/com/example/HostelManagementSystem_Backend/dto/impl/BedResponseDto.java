package com.example.HostelManagementSystem_Backend.dto.impl;

import com.example.HostelManagementSystem_Backend.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BedResponseDto implements SuperDto {

    private String bedId;
    private String type;
    private String description;
    private Boolean availability;
    private String roomId;
    private int roomNo;
    private String studentId;
    private String studentName;
}
