package com.example.HostelManagementSystem_Backend.entity.impl;

import com.example.HostelManagementSystem_Backend.entity.SuperEntity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
@Entity
@Table(name = "parent")
public class ParentEntity implements SuperEntity {

    @Id
    @Column(name = "parent_id", length = 10)
    private String parentId;

    @Column(name = "parent_NIC", length = 12, unique = true, nullable = false)
    private String parentNIC;

    @Column(name = "parent_name", nullable = false, length = 100)
    private String parentName;

    @Column(name = "parent_tel", nullable = false, length = 15)
    private String parentTel;

    @Column(name = "parent_address", nullable = false, length = 255)
    private String parentAddress;

    @Column(name = "parent_email", length = 100)
    private String parentEmail;

    @OneToMany(mappedBy = "parent")
    private List<StudentEntity> students;
}