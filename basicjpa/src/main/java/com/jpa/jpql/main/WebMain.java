package com.jpa.jpql.main;

import com.jpa.common.JPATemplate;
import com.jpa.jpql.controller.CriteriaController;
import com.jpa.jpql.controller.WebController;
import jakarta.persistence.EntityManager;

import java.util.Map;

public class WebMain {
    public static void main(String[] args) {
        EntityManager em= JPATemplate.getWebEntityFactory().createEntityManager();
//        WebController wc=new WebController();
//        wc.basicJPQL(em);
        //wc.useWhere(em);
        //wc.groupByFunction(em);
        //wc.paginationTest(em);
//        wc.joinTest(em);
        CriteriaController cc = new CriteriaController();
//        cc.basicCreteria(em);
       // cc.criteriaWhere(em);
//        cc.orderTest(em);

//        cc.joinTest(em);
        // cc.dynamicQuery(em, Map.of("title","안녕","writer","admin"));
        //cc.dynamicQuery(em, Map.of("title","안녕"));
        cc.dynamicQuery(em, Map.of("writer","abcde","content","갑"));
        em.close();
    }

}
