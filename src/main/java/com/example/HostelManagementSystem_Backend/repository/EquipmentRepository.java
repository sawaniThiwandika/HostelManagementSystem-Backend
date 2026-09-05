package com.example.HostelManagementSystem_Backend.repository;


import com.example.HostelManagementSystem_Backend.entity.impl.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<EquipmentEntity, String> {

    List<EquipmentEntity> findByRoom_RoomId(String roomId);
}
