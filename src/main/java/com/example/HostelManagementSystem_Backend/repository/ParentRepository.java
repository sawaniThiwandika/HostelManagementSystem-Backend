package com.example.HostelManagementSystem_Backend.repository;


import com.example.HostelManagementSystem_Backend.entity.impl.ParentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParentRepository extends JpaRepository<ParentEntity, String> {

    Optional<ParentEntity> findByParentNIC(String parentNIC);

    boolean existsByParentNIC(String parentNIC);
}
