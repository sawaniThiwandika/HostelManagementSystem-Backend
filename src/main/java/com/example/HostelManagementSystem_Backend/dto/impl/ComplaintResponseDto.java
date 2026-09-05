package com.example.HostelManagementSystem_Backend.dto.impl;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintResponseDto {

    private String complaintId;
    private String studentName;
    private String studentId;
    private String roomId;
    private String description;
    private LocalDate date;
    private String priority;
}
