package com.example.HostelManagementSystem_Backend.entity.impl;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "payment")
@Data
public class PaymentEntity {
    @Id
    @Column(name = "payment_id", length = 10)
    private String paymentId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private StaffEntity staff;

    private Double fee;

    private LocalDate date;

    private LocalTime time;

    @Column(name = "billing_month", length = 20)
    private String billingMonth;

    @Column(name = "billing_year")
    private Integer billingYear;
}