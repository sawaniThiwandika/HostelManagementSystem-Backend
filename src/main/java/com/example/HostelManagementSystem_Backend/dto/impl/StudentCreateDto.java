package com.example.HostelManagementSystem_Backend.dto.impl;

import com.example.HostelManagementSystem_Backend.dto.SuperDto;
import jakarta.validation.constraints.Email;
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
public class StudentCreateDto implements SuperDto {

    @NotBlank(message = "Student name is required")
    private String name;

    @NotBlank(message = "NIC is required")
    @Pattern(
            regexp = "^([0-9]{9}[vVxX]|[0-9]{12})$",
            message = "Invalid NIC format. Must be 9 digits followed by V/X or 12 digits."
    )
    private String nic;

    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Telephone number is required")
    private String tel;

    @NotBlank(message = "Street address is required")
    private String streetAddress;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Province is required")
    private String province;

    @NotBlank(message = "Postal code is required")
    private String postalCode;

    @NotBlank(message = "Parent ID is required")
    private String parentId;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;

    private String specialThings;
    private String jobEducationInstitute;
    private String positionFaculty;
    private String batch;
    private String degree;
}