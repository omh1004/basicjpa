package com.jpa.jpql.controller;

import com.jpa.jpql.entity.WebMemberEntity;
import com.jpa.model.entity.MemberEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.w3c.dom.CDATASection;

import java.lang.reflect.Member;
import java.util.Arrays;
import java.util.List;

public class MemberController {
    public void searchjpql(EntityManager em){

        String jpql = "select m.userName from WebMemberEntity m";


        Query query1
                = em.createQuery(jpql);
        List<Object> titleList=query1.getResultList();
        titleList.forEach(System.out::println);

        jpql ="select m.userId,m.password from WebMemberEntity m";
        Query query2
                = em.createQuery(jpql);

        List<Object[]> titleList2 = query2.getResultList();
        titleList2.forEach(
                data->{
                    System.out.println(Arrays.toString(data));
                }
        );

        jpql ="select m from WebMemberEntity m where m.userName like :sung";

        TypedQuery<WebMemberEntity> query3= em.createQuery(jpql,WebMemberEntity.class);

        query3.setParameter("sung","%김%");

        query3.getResultList().forEach((data->{
            System.out.println(data.toString());
        }));




        jpql ="select m from WebMemberEntity m where m.userName like :sung";

        TypedQuery<WebMemberEntity> query4= em.createQuery(jpql,WebMemberEntity.class);

        query4.setParameter("sung","%은%");

        query4.getResultList().forEach((data->{
            System.out.println(data.toString());
        }));

        jpql =
                """
                     select  
                            b,m 
                   from board b join b.boardWriter m 
                """;

//        Query query4
//                = em.createQuery(jpql);
//        Query query5
//                = em.createQuery(jpql);
//        Query query6
//                = em.createQuery(jpql);
//
//        jpql ="select m.userId,m.password from WebMemberEntity m";
//        jpql ="select m.userId,m.password from WebMemberEntity m";
//        jpql ="select m.userId,m.password from WebMemberEntity m";


    }
}
