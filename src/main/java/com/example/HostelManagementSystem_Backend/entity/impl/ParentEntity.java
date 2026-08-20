package com.example.HostelManagementSystem_Backend.entity.impl;

import com.example.HostelManagementSystem_Backend.entity.impl.StudentEntity;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "parent")
@Data
public class ParentEntity {
    @Id
    @Column(name = "parent_id", length = 10)
    private String parentId;

    @Column(name = "parent_name", nullable = false, length = 100)
    private String parentName;

    @Column(name = "parent_tel", length = 15)
    private String parentTel;

    @Column(name = "parent_address", length = 255)
    private String parentAddress;

    @Column(name = "parent_email", length = 100)
    private String parentEmail;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<StudentEntity> students;
}