package com.example.HostelManagementSystem_Backend.dto.impl;
import com.example.HostelManagementSystem_Backend.dto.SuperDto;
import com.example.HostelManagementSystem_Backend.enums.StaffCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffCreateDto implements SuperDto {

    @NotBlank(message = "NIC is required")
    @Pattern(
            regexp = "^([0-9]{9}[vVxX]|[0-9]{12})$",
            message = "Invalid NIC format. Must be 9 digits followed by V/X or 12 digits."
    )
    private String nic;

    @NotBlank(message = "Telephone number is required")
    private String tel;

    @NotBlank(message = "Address is required")
    private String address;

    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be in the past")
    private LocalDate bDate;

    @NotNull(message = "Staff category is required")
    private StaffCategory category;

    @NotBlank(message = "Hostel ID is required")
    private String hostelId;

}