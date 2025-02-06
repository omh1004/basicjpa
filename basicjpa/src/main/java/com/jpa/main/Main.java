package com.jpa.main;

import com.jpa.common.JPATemplate;
import com.jpa.controller.BasicJpaController;
import com.jpa.controller.EmployeeController;
import com.jpa.controller.StudentController;
import com.jpa.model.entity.EmployeeEntity;
import com.jpa.model.entity.SampleEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //EntityManager객체 가져오기
        EntityManager entityManager=JPATemplate.getEntityManagerFactory()
                .createEntityManager();
        BasicJpaController controller=new BasicJpaController();
//        controller.basicTest(entityManager);
//        controller.searchTest(entityManager);
//        controller.insertMember(entityManager);
//        controller.selectMember(entityManager);
//        controller.insertMember2(entityManager);
//        controller.selectMember2(entityManager,21L);

        StudentController studentController=new StudentController();
//        studentController.saveStudent(entityManager);
//        studentController.updateStudent(entityManager,1L);
//        studentController.deleteStudent(entityManager,1L);
//        studentController.findStudentByNo(entityManager,13L);
//        studentController.findStudent(entityManager);

//        studentController.oneToOneTest(entityManager);

//        findByStudentNameAndGrade("아무개",3);
//        studentController.OneToManyTest(entityManager);
        //studentController.oneToManySelect(entityManager,1L);
        //studentController.oneToManyTest2(entityManager);

//        EmployeeController ec=new EmployeeController();
//        ec.saveData(entityManager);
//        ec.searchData(entityManager);

        studentController.manyToManyTest(entityManager);

    }
}


