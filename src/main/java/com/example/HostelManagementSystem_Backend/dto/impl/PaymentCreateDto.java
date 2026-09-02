package com.example.HostelManagementSystem_Backend.dto.impl;

import com.example.HostelManagementSystem_Backend.dto.SuperDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCreateDto implements SuperDto {
    @NotBlank(message = "Student ID is required")
    private String studentId;

    @NotBlank(message = "Staff ID is required")
    private String staffId;

    @NotNull(message = "Fee amount is required")
    private Double fee;

    @NotBlank(message = "Billing month is required")
    private String billingMonth;

    @NotNull(message = "Billing year is required")
    private Integer billingYear;
}
