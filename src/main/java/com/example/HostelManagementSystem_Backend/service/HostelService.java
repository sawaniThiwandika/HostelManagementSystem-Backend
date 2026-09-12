package com.example.HostelManagementSystem_Backend.service;

import com.example.HostelManagementSystem_Backend.entity.impl.HostelEntity;

public interface HostelService {
    HostelEntity createHostel(HostelEntity hostel, String username);
}
