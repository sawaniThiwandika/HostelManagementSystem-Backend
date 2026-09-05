package com.example.HostelManagementSystem_Backend.repository;


import com.example.HostelManagementSystem_Backend.entity.impl.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, String> {

    List<PaymentEntity> findByStudent_StudentId(String studentId);

    List<PaymentEntity> findByStaff_StaffId(String staffId);

    List<PaymentEntity> findByBillingMonthAndBillingYear(String billingMonth, Integer billingYear);
}