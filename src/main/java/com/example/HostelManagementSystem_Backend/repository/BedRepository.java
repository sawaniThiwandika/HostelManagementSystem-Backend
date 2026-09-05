package com.example.HostelManagementSystem_Backend.repository;

import com.example.HostelManagementSystem_Backend.entity.impl.BedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BedRepository extends JpaRepository<BedEntity, String> {

    // Derived method query option:
    List<BedEntity> findByRoom_RoomIdAndStudentIsNull(String roomId);

    // OR explicit JPQL query option:
    @Query("SELECT b FROM BedEntity b WHERE b.room.roomId = :roomId AND b.student IS NULL")
    List<BedEntity> findAvailableBedsByRoomId(@Param("roomId") String roomId);

    // Find all unallocated beds across the system
    List<BedEntity> findByStudentIsNull();
}
