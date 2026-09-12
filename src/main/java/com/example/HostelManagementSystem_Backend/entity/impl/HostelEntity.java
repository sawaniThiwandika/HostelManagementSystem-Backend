package com.example.HostelManagementSystem_Backend.entity.impl;

import com.example.HostelManagementSystem_Backend.entity.SuperEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(length = 255 ,nullable = false)
    private String address;

    @Column(length = 15,nullable = false)
    private String tel;

    @Column(length = 100,nullable = false)
    private String email;

    @Column(length = 100,nullable = false)
    private String location;

    @ManyToOne
    @JoinColumn(name = "owner_id",nullable = false)
    @JsonBackReference("owner-hostels")
    private OwnerEntity owner;

    @OneToMany(mappedBy = "hostel", cascade = CascadeType.ALL)
    @JsonManagedReference("hostel-rooms")
    private List<RoomEntity> rooms;
}
