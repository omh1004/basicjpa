package com.jpa.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "department")
@SequenceGenerator(name = "seqdepartno",sequenceName = "seq_depart_no",allocationSize = 1, initialValue = 1)
public class DepartmentEntity {

    @Id
    @GeneratedValue(generator = "seqdepartno",strategy = GenerationType.SEQUENCE)
    private long deptNo;

    @Column(columnDefinition = "varchar2(20) not null")
    private String deptName;

    @OneToMany(mappedBy = "depart")
    @ToString.Exclude
    private List<EmployeeEntity> emploees;
}
