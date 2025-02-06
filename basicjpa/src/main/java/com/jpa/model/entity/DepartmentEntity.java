package com.jpa.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name="department")
@SequenceGenerator(name="seqDepartmentno",
        sequenceName = "seq_department_no",
        allocationSize = 1)
public class DepartmentEntity {
    @Id
    @GeneratedValue(generator = "seqDepartmentno",strategy = GenerationType.SEQUENCE)
    private Long departmentNo;

    @Column(nullable = false)
    private String departmentName;

    @OneToMany(mappedBy="department")
    private List<EmployeeEntity> employees;
}
