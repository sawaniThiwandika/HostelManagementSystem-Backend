package com.example.HostelManagementSystem_Backend.dto.impl;

import com.example.HostelManagementSystem_Backend.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDto implements SuperDto {
    private String paymentId;
    private Double fee;
    private LocalDate date;
    private LocalTime time;
    private String billingMonth;
    private Integer billingYear;

    private String studentId;
    private String studentName;

    private String staffId;
    private String staffName;
}
