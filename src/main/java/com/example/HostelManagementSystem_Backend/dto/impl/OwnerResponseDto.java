package com.example.HostelManagementSystem_Backend.dto.impl;

import com.example.HostelManagementSystem_Backend.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerResponseDto implements SuperDto {

    private String ownerId;

    private String ownerName;

    private String nic;

    private String tel;

    private String address;

    private String workplace;

    private String jonTitle;

    private String email;

    private List<HostelSummaryDto> hostels;
}
