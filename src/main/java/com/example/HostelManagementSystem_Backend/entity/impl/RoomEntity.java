package com.example.HostelManagementSystem_Backend.entity.impl;

import com.example.HostelManagementSystem_Backend.entity.SuperEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="room")
public class RoomEntity implements SuperEntity {
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
    @JsonBackReference("hostel-rooms")
    private HostelEntity hostel;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<BedEntity> beds;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<EquipmentEntity> equipmentList;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<ComplaintEntity> complaints;
}
