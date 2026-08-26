package com.example.HostelManagementSystem_Backend.entity.impl;

import com.example.HostelManagementSystem_Backend.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="hostel")
public class HostelEntity implements SuperEntity {
    @Id
    @Column(name = "hostel_id", length = 10)
    private String hostelId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 255)
    private String address;

    @Column(length = 15)
    private String tel;

    @Column(length = 100)
    private String email;

    @Column(length = 100)
    private String location;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private OwnerEntity owner;

    @OneToMany(mappedBy = "hostel", cascade = CascadeType.ALL)
    private List<RoomEntity> rooms;
}
