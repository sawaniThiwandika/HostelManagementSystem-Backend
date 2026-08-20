package com.example.HostelManagementSystem_Backend.entity.impl;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name="complaint")
public class ComplaintEntity {
    @Id
    @Column(name = "complaint_id", length = 10)
    private String complaintId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;

    @Column(length = 255)
    private String description;

    private LocalDate date;

    @Column(length = 50)
    private String priority;
}
