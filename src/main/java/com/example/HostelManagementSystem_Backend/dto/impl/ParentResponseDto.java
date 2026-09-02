package com.example.HostelManagementSystem_Backend.dto.impl;

import com.example.HostelManagementSystem_Backend.dto.SuperDto;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentResponseDto implements SuperDto {

    private String parentId;
    private String parentName;
    private String parentTel;
    private String parentAddress;
    private String parentEmail;
    private List<StudentSummaryDto> students;

}
