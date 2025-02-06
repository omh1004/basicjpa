package com.jpa.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


@Entity
@Table(name = "employee")
@SequenceGenerator(name = "seqempen" , sequenceName = "seq_emp_no",allocationSize = 1, initialValue = 1)
public class EmployeeEntity {

        @Id
        @GeneratedValue(generator = "segempen",strategy = GenerationType.SEQUENCE)
        private long empNo;

        private String empName;
        private Integer empAge;
        private String address;
        private Integer gumyu;

        //private
        @ManyToOne
        private DepartmentEntity depart;



}
