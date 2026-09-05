package com.example.HostelManagementSystem_Backend.repository;

import com.example.HostelManagementSystem_Backend.entity.impl.ComplaintEntity;
import com.example.HostelManagementSystem_Backend.enums.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<ComplaintEntity, String> {

    List<ComplaintEntity> findByStudent_StudentId(String studentId);

    List<ComplaintEntity> findByRoom_RoomId(String roomId);

    List<ComplaintEntity> findByPriority(Priority priority);

    List<ComplaintEntity> findByRoom_Hostel_HostelId(String hostelId);
}