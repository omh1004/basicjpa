package com.jpa.common;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import jakarta.persistence.EntityManager;

public class JPATemplate {

    private static EntityManagerFactory emf;


    public JPATemplate() {}
    public static EntityManagerFactory getEntityManagerFactory(){
        if(emf == null)
            emf = Persistence.createEntityManagerFactory("basicjpa");
        return emf;
    }
}
