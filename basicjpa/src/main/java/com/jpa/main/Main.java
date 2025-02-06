package com.jpa.main;

import com.jpa.common.JPATemplate;
import com.jpa.controller.BasicJpaController;
import com.jpa.controller.EmployeeController;
import com.jpa.controller.StudentController;
import jakarta.persistence.EntityManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {

       // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       // Scanner scanner = new Scanner(System.in);

     //   System.out.println("번호입력 : ");
//        Long input = Long.parseLong(br.readLine());
 //       long input2 = scanner.nextLong();
//        scanner.nextLine();
      //  scanner.close();



        //EntityManager객체 가져오기
        EntityManager entityManager=JPATemplate.getEntityManagerFactory()
                .createEntityManager();
     //   BasicJpaController controller=new BasicJpaController();
//        controller.basicTest(entityManager);
//        controller.searchTest(entityManager);
      //  controller.insertMember(entityManager);
        //controller.selectMember(entityManager);
       // controller.insertMember2(entityManager);
        //controller.selectMember2(entityManager,52L);
        //controller.insertMember3(entityManager);

        StudentController controller= new StudentController();

       // controller.saveStudent(entityManager);
        //controller.findStudent(entityManager);
        //controller.findStudentByNo(entityManager,11L);
        //controller.updateStudent(entityManager,input2);
       // controller.deleteStudent(entityManager,input2);

       // controller.OneToManyTest(entityManager);
        //controller.oneToManySelect(entityManager,1L);
        controller.oneToManyTest2(entityManager);

        EmployeeController employeeController = new EmployeeController();

        employeeController.saveEmployee(entityManager);
    }
}
