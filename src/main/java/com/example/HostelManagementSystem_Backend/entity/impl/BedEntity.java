package com.example.HostelManagementSystem_Backend.entity.impl;

import com.example.HostelManagementSystem_Backend.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="Bed")
public class BedEntity implements SuperEntity {
    @Id
    @Column(name = "bed_id", length = 10)
    private String bedId;

    @Column(length = 50)
    private String type;

    @Column(length = 255)
    private String description;

    @Column()
    private Boolean availability;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;
}
