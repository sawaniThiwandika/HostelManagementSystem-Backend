package com.example.HostelManagementSystem_Backend.repository;

import com.example.HostelManagementSystem_Backend.entity.impl.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, String> {

    @Query("SELECT r.roomId FROM RoomEntity r ORDER BY r.roomId DESC LIMIT 1")
    Optional<String> findLastRoomId();
}