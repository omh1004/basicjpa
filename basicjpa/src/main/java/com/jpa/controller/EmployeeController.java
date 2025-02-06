package com.jpa.controller;

import com.jpa.model.entity.DepartmentEntity;
import com.jpa.model.entity.EmployeeEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class EmployeeController {

    public void saveData(EntityManager em){
        EntityTransaction et=em.getTransaction();
        et.begin();
        DepartmentEntity dept=DepartmentEntity.builder()
                .departmentName("개발팀")
                .build();
        DepartmentEntity dept1=DepartmentEntity.builder()
                .departmentName("경영팀")
                .build();
        em.persist(dept);
        em.persist(dept1);

        EmployeeEntity e= EmployeeEntity.builder()
                .employeeAge(19)
                .employeeName("유병승")
                .employeeAddress("경기도 시흥시")
                .employeeSalary(100)
//                .department(dept)
                .build();
        //편의 기능을 제공하는 setter로 데이터 저장
        e.setDepartment(dept);

        EmployeeEntity e1= EmployeeEntity.builder()
                .employeeAge(27)
                .employeeName("이민영")
                .employeeAddress("경기도 군포시")
                .employeeSalary(200)
//                .department(dept)
                .build();
        e1.setDepartment(dept);

        EmployeeEntity e2= EmployeeEntity.builder()
                .employeeAge(25)
                .employeeName("염상균")
                .employeeAddress("경기도 안양시")
                .employeeSalary(150)
//                .department(dept1)
                .build();
        e2.setDepartment(dept1);

        em.persist(e);
        em.persist(e1);
        em.persist(e2);

//        dept.setEmployees(List.of(e,e1));
//        dept1.setEmployees(List.of(e2));

        et.commit();

    }

    public void searchData(EntityManager em){
//        em.clear();
        DepartmentEntity findDepartment=
                em.find(DepartmentEntity.class,1);
        System.out.println(findDepartment);
        findDepartment=
                em.find(DepartmentEntity.class,2);
        System.out.println(findDepartment);


    }

}
