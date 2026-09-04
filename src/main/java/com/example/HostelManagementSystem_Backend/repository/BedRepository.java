package com.example.HostelManagementSystem_Backend.repository;

import com.example.HostelManagementSystem_Backend.entity.impl.BedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BedRepository extends JpaRepository<BedEntity,String> {
    //Find beds by availability status
    List<BedEntity> findByAvailability(Boolean availability);

    // Find all beds belonging to a specific room ID
    List<BedEntity> findByRoom_RoomId(String roomId);

    //Fetch only available beds for a given room
    @Query("SELECT b FROM BedEntity b WHERE b.room.roomId = :roomId AND b.availability = true")
    List<BedEntity> findAvailableBedsByRoomId(@Param("roomId") String roomId);
}
