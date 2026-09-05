package com.example.HostelManagementSystem_Backend.entity.impl;

import com.example.HostelManagementSystem_Backend.entity.SuperEntity;
import com.example.HostelManagementSystem_Backend.enums.StaffCategory;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "staff")
@Data
public class StaffEntity implements SuperEntity {
    @Id
    @Column(name = "staff_id", length = 10)
    private String staffId;

    @Column(unique = true, length = 12)
    private String nic;

    @Column(length = 15)
    private String tel;

    @Column(length = 255)
    private String address;

    @Column(name = "b_date")
    private LocalDate bDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "job_title", length = 50, nullable = false)
    private StaffCategory category;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private List<PaymentEntity> payments;

    @ManyToOne
    @JoinColumn(name = "hostel_id")
    private HostelEntity hostel;
}
