package com.example.HostelManagementSystem_Backend.repository;


import com.example.HostelManagementSystem_Backend.entity.impl.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, String> {

    Optional<StudentEntity> findByNic(String nic);

    Optional<StudentEntity> findByEmail(String email);

    boolean existsByNic(String nic);

    boolean existsByEmail(String email);

    List<StudentEntity> findByParent_ParentId(String parentId);
}