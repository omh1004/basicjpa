package com.jpa.controller;

import com.jpa.model.entity.DepartmentEntity;
import com.jpa.model.entity.EmployeeEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class EmployeeController {

    public void saveEmployee(EntityManager em){
        EntityTransaction et  = em.getTransaction();

        et.begin();

        DepartmentEntity depart = DepartmentEntity.builder().deptName("개발").build();
        em.persist(depart);

        EmployeeEntity saveEmployee = EmployeeEntity.builder()
                .empName("유병승")
                .empAge(19)
                .address("경기도시흥시")
                .gumyu(100)
                .depart(depart)
                .build();
        em.persist(saveEmployee);
        EmployeeEntity saveEmployee1 = EmployeeEntity.builder()
                .empName("오민현")
                .empAge(22)
                .address("경기도안양시")
                .gumyu(200)
                .depart(depart)
                .build();

        em.persist(saveEmployee1);

        et.commit();
        em.clear();

        DepartmentEntity departmentEntity = em.find(DepartmentEntity.class,1L);

        departmentEntity.getEmploees().forEach(System.out::println);

        EmployeeEntity employeeEntity = em.find(EmployeeEntity.class,1L);
        System.out.println(employeeEntity.getDepart().getDeptName());
    }
}
