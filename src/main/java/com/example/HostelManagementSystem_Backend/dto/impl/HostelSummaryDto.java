package com.example.HostelManagementSystem_Backend.dto.impl;

import com.example.HostelManagementSystem_Backend.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HostelSummaryDto implements SuperDto {

        private String hostelId;
        private String name;
        private String address;
        private String tel;
        private String email;
        private String location;
}
