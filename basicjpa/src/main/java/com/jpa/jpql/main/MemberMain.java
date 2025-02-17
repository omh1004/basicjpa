package com.jpa.jpql.main;

import com.jpa.common.JPATemplate;
import com.jpa.jpql.controller.MemberController;
import jakarta.persistence.EntityManager;

public class MemberMain {

     public static void main(String[] args) {
         EntityManager em= JPATemplate.getWebEntityFactory().createEntityManager();

         MemberController mc = new MemberController();

         mc.searchjpql(em);

         em.close();


    }

}
