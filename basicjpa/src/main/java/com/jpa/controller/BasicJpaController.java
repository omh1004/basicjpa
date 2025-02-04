package com.jpa.controller;

import com.jpa.common.Role;
import com.jpa.model.entity.MemberEntity;
import com.jpa.model.entity.SampleEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class BasicJpaController {
    public void basicTest(EntityManager em){
        EntityTransaction et=em.getTransaction();
        et.begin();//트렌젝션 시작
        //비영속 상태
        SampleEntity sample=SampleEntity.builder()
                .id(3L)
                .data("첫번째엔티티")
                .build();

        System.out.println(sample);
        em.persist(sample);//-> sample객체를 영속화하기 -> jpa에 의해 관리

        SampleEntity sample2 = SampleEntity.builder()
                .id(4L).data("우와 신기해!")
                .build();

        em.persist(sample2);

        et.commit();// 영속성 컨텍스트가 가지고 있는 sql문을 실행

    }

    public void searchTest(EntityManager em){
        EntityTransaction et=em.getTransaction();
        et.begin();
        SampleEntity sample =  em.find(SampleEntity.class, 1L);
        sample.setData("내가수정한것");
        em.persist(sample);
        et.commit();
    }

    public void insertMember(EntityManager em){
        EntityTransaction et=em.getTransaction();
        et.begin();
        MemberEntity m = MemberEntity.builder()
                .memberId("admin").memberPwd("1234").memberName("관리자")
                .memberAge(19).role(Role.ADMIN).build();
        em.persist(m);
        et.commit();
    }

    public void selectMember(EntityManager em){
        MemberEntity member =  em.find(MemberEntity.class, 1L);
        System.out.println(member);
    }

}
