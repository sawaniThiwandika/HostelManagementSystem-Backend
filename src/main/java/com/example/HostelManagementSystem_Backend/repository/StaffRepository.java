package com.example.HostelManagementSystem_Backend.repository;

import com.example.HostelManagementSystem_Backend.entity.impl.StaffEntity;
import com.example.HostelManagementSystem_Backend.enums.StaffCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<StaffEntity, String> {

    Optional<StaffEntity> findByNic(String nic);

    boolean existsByNic(String nic);

    List<StaffEntity> findByHostel_HostelId(String hostelId);

    List<StaffEntity> findByCategory(StaffCategory category);

    List<StaffEntity> findByHostel_HostelIdAndCategory(String hostelId, StaffCategory category);
}