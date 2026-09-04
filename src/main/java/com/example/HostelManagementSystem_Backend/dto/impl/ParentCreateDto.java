package com.example.HostelManagementSystem_Backend.dto.impl;

import com.example.HostelManagementSystem_Backend.dto.SuperDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentCreateDto implements SuperDto {

    @NotBlank(message = "Parent name is required")
    private String parentName;
    @NotBlank(message = "Parent NIC is required")
    @Pattern(
            regexp = "^([0-9]{9}[vVxX]|[0-9]{12})$",
            message = "Invalid NIC format. Must be 9 digits followed by V/X or 12 digits."
    )
    private String parentNIC;
    @NotBlank(message = "Parent contact number is required")
    private String parentTel;
    @NotBlank(message = "parent street address is required")
    private String streetAddress;
    @NotBlank(message = "parent city is required")
    private String city;
    @NotBlank(message = "parent province is required")
    private String province;
    @NotBlank(message = "parent postel code is required")
    private String postelCode;
    private String parentEmail;


}
