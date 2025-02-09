package com.jpa.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

@Data@AllArgsConstructor@NoArgsConstructor@Builder

@Entity
@Table(name="employee")
@SequenceGenerator(name="seqEmployeeno",
        sequenceName = "seq_employee_no",
        allocationSize = 1)
public class EmployeeEntity {
    @Id
    @GeneratedValue(generator = "seqEmployeeno",
            strategy = GenerationType.SEQUENCE)
    private Long employeeNo;

    @Column(nullable = false)
    private String employeeName;

    private Integer employeeAge;

    private String employeeAddress;

    @Column(name="employee_salary")
    private Integer employeeSalary;

    @ToString.Exclude
    @ManyToOne
    //@Column(name="department_ref")//일반 컬럼을 설정할때
    @JoinColumn(name="department_ref")
    private DepartmentEntity department;


    public void setDepartment(DepartmentEntity department){
        if(this.department!=null
                &&this.department.getEmployees()!=null){
            this.department.getEmployees().remove(this);
        }

        this.department=department;

        if(this.department.getEmployees()==null) {
            this.department.setEmployees(new ArrayList());
        }
         this.department.getEmployees().add(this);

    }

}
