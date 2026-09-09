package com.example.HostelManagementSystem_Backend.dto.impl;

import com.example.HostelManagementSystem_Backend.dto.SuperDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerCreateDto implements SuperDto {

    @Valid
    @NotNull(message = "User signup details are required")
    private UserSignUpDto user;
    @NotBlank(message = "Owner name is required")
    private String ownerName;
    @NotBlank(message = "Owner NIC is required")
    @Pattern(
            regexp = "^([0-9]{9}[vVxX]|[0-9]{12})$",
            message = "Invalid NIC format. Must be 9 digits followed by V/X or 12 digits."
    )
    private String nic;
    @NotBlank(message = "Owner contact number is required")
    private String tel;
    @NotBlank(message = "Owner address is required")
    private String address;
    @NotBlank(message = "Owner workplace is required")
    private String workplace;
    @NotBlank(message = "Owner job title is required")
    private String jobTitle;
    @NotBlank(message = "Owner email is required")
    private String email;
}
