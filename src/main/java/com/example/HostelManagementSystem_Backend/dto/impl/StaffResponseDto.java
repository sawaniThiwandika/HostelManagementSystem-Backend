package com.example.HostelManagementSystem_Backend.dto.impl;

import com.example.HostelManagementSystem_Backend.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffResponseDto implements SuperDto {

    private String staffId;
    private String nic;
    private String tel;
    private String address;
    private LocalDate bDate;
    private String jobTitle;
}