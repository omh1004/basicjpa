package com.jpa.jpql.main;

import com.jpa.common.JPATemplate;
import com.jpa.jpql.controller.WebController;
import jakarta.persistence.EntityManager;

public class WebMain {
    public static void main(String[] args) {
        EntityManager em= JPATemplate.getWebEntityFactory().createEntityManager();
        WebController wc=new WebController();
//        wc.basicJPQL(em);
        wc.useWhere(em);
        em.close();
    }

}
