package com.example.HostelManagementSystem_Backend.repository;

import com.example.HostelManagementSystem_Backend.entity.impl.HostelEntity;
import com.example.HostelManagementSystem_Backend.entity.impl.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HostelRepository extends JpaRepository<HostelEntity,String> {
    List<HostelEntity> findByOwner(OwnerEntity owner);
    @Query(value = "SELECT hostel_id FROM hostel ORDER BY hostel_id DESC LIMIT 1", nativeQuery = true)
    Optional<String> findLastHostelId();
}
