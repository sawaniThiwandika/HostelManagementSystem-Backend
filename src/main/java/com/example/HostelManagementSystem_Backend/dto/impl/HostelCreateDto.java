package com.example.HostelManagementSystem_Backend.dto.impl;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HostelCreateDto {
    @NotBlank(message = "hostel name is required")
    private String name;
    @NotBlank(message = "hostel street address is required")
    private String streetAddress;
    @NotBlank(message = "hostel city is required")
    private String city;
    @NotBlank(message = "hostel province is required")
    private String province;
    @NotBlank(message = "hostel postel code is required")
    private String postelCode;
    @NotBlank(message = "hostel telephone number is required")
    private String tel;
    private String email;
    @NotBlank(message = "hostel location is required")
    private String location;
    @NotBlank(message = "hostel owner id required")
    private String ownerId;

}
