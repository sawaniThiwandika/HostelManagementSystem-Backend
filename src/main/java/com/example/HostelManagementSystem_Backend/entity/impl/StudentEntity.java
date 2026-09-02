package com.example.HostelManagementSystem_Backend.entity.impl;

import com.example.HostelManagementSystem_Backend.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name="student")
public class StudentEntity implements SuperEntity {
    @Id
    @Column(name = "student_id", length = 10)
    private String studentId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(unique = true, length = 12)
    private String nic;

    @Column(length = 100, unique = true, nullable = false)
    private String email;

    @Column(length = 15 , nullable = false)
    private String tel;

    @Column(length = 255, nullable = false)
    private String address;

    // 1. Added the parent field here
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private ParentEntity parent;

    // 2. dob is completely separate with only @Column
    @Column(name = "dob"  , nullable = false)
    private LocalDate dob;

    @Column(name = "special_things", length = 255)
    private String specialThings;

    @Column(name = "job_education_institute", length = 150, nullable = false)
    private String jobEducationInstitute;

    @Column(name = "position_faculty", length = 100, nullable = false)
    private String positionFaculty;

    @Column(length = 50)
    private String batch;

    @Column(length = 100)
    private String degree;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<PaymentEntity> payments;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<ComplaintEntity> complaints;
}