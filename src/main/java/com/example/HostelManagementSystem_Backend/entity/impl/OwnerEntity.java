package com.example.HostelManagementSystem_Backend.entity.impl;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name="owner")
@Data
public class OwnerEntity {
    @Id
    @Column(name = "owner_id", length = 10)
    private String ownerId;

    @Column(name = "owner_name", nullable = false, length = 100)
    private String ownerName;

    @Column(unique = true, nullable = false, length = 12)
    private String nic;

    @Column(length = 15)
    private String tel;

    @Column(length = 255)
    private String address;

    @Column(length = 50)
    private String postron;

    @Column(length = 100)
    private String job;

    @Column(length = 100)
    private String email;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<HostelEntity> hostels;
}
