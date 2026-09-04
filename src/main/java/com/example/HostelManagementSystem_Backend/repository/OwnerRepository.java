package com.example.HostelManagementSystem_Backend.repository;

import com.example.HostelManagementSystem_Backend.entity.impl.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerEntity, String> {

    // Find an owner by NIC
    Optional<OwnerEntity> findByNic(String nic);
    boolean existsByNic(String nic);

    // Check if an owner already exists with the given email address
    boolean existsByEmail(String email);
}