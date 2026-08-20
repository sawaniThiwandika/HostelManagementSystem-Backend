package com.example.HostelManagementSystem_Backend.entity.impl;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="Bed")
public class BedEntity {
    @Id
    @Column(name = "bed_id", length = 10)
    private String bedId;

    @Column(length = 50)
    private String type;

    @Column(length = 255)
    private String description;

    private Boolean availability;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;
}
