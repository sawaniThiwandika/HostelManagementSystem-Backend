package com.example.HostelManagementSystem_Backend.dto.impl;


import com.example.HostelManagementSystem_Backend.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentSummaryDto implements SuperDto {

    private String studentId;
    private String name;
    private String tel;
    private String jobEducationInstitute;
    private String positionFaculty;
    private String roomId;
}