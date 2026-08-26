package com.example.HostelManagementSystem_Backend.entity.impl;

import com.example.HostelManagementSystem_Backend.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="equipment")
public class EquipmentEntity implements SuperEntity {
    @Id
    @Column(name = "equipment_id", length = 10)
    private String equipmentId;

    @Column(length = 50)
    private String type;

    @Column(length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;
}
