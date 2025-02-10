package com.jpa.controller;

import com.jpa.model.영속성전이.PersistenceChildEntity;
import com.jpa.model.영속성전이.PersistenceParentEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class PersistenceTestController {

    public void testPersistence(EntityManager em){
        EntityTransaction et=em.getTransaction();
        et.begin();
        PersistenceParentEntity ppe=new PersistenceParentEntity();
        ppe.setNo(1L);
        ppe.setTitle("첫번째 부모");

        PersistenceChildEntity child1=new PersistenceChildEntity();
        child1.setId(1L);
        child1.setName("첫번째 자식");

        PersistenceChildEntity child2=new PersistenceChildEntity();
        child2.setId(2L);
        child2.setName("두번째 자식");

        PersistenceChildEntity child3=new PersistenceChildEntity();
        child3.setId(3L);
        child3.setName("세번째 자식");

        ppe.setChildren(List.of(child1,child2,child3));

        em.persist(ppe);

        et.commit();

        em.clear();

        et.begin();
        PersistenceParentEntity findParent=em.find(PersistenceParentEntity.class,1L);
        //삭제했을때 어떻게 될까?
        em.remove(findParent);

        et.commit();

    }
}
