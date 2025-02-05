package com.jpa.controller;

import com.jpa.common.Gender;
import com.jpa.model.dto.Address;
import com.jpa.model.entity.MemberEntity;
import com.jpa.model.entity.SampleEntity;
import com.jpa.model.entity.StudentEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.Date;

public class StudentController {

    public void saveStudent(EntityManager em){
        //insert문 실행
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        StudentEntity studentEntity = StudentEntity.builder().studentName("오민현")
                .grade(1).classNum(2).birthDay(new Date()).gender(Gender.M)
                .address(new Address("화성특례시","경기도","남양읍","12340222"))
                .build();

        em.persist(studentEntity);
        tx.commit();
    }

    public void findStudentByNo(EntityManager em,Long no){
        StudentEntity student =  em.find(StudentEntity.class, no);
        System.out.println(student);
    }



    public void findStudent(EntityManager em){
        String jpql=  "select s from StudentEntity s";
        TypedQuery<StudentEntity> query  =  em.createQuery(jpql,StudentEntity.class);
        query.getResultList().forEach(System.out::println);
    }

    public void updateStudent(EntityManager em,Long id){
        EntityTransaction et=em.getTransaction();
        et.begin();
        StudentEntity studentEntity = em.find(StudentEntity.class, id);
        studentEntity.setGrade(2);

        et.commit();
    }

    public void deleteStudent(EntityManager em,Long id){
        EntityTransaction et=em.getTransaction();
        et.begin();
        StudentEntity student =  em.find(StudentEntity.class, id);
        em.remove(student);
        et.commit();

    }

}
