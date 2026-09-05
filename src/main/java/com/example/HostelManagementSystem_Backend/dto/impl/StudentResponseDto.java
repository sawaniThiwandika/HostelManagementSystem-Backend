package com.example.HostelManagementSystem_Backend.dto.impl;


import com.example.HostelManagementSystem_Backend.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDto implements SuperDto {

    private String studentId;
    private String name;
    private String nic;
    private String email;
    private String tel;
    private String address;
    private LocalDate dob;
    private String specialThings;
    private String jobEducationInstitute;
    private String positionFaculty;
    private String batch;
    private String degree;
    private String parentId;
    private String parentName;
    private String bedId;
    private String roomId;
}