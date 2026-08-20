package com.example.HostelManagementSystem_Backend.entity.impl;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="room")
public class RoomEntity {
    @Id
    @Column(name = "room_id", length = 10)
    private String roomId;

    @Column(unique = true)
    private Integer num;

    private Integer floor;

    @Column(length = 255)
    private String category;

    private Integer size;

    @Column(length = 255)
    private String description;

    private Double fee;

    @ManyToOne
    @JoinColumn(name = "hostel_id")
    private HostelEntity hostel;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<BedEntity> beds;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<EquipmentEntity> equipmentList;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<ComplaintEntity> complaints;
}
