package com.jpa.jpql.controller;

import com.jpa.jpql.entity.BoardEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Scanner;

public class WebController {

    public void basicJPQL(EntityManager em){
        //JPQL이용하기

        //기본 select문 작성하기
        //1. 대소문자를 구분함.
        //2. 대상이 엔티티객체 from절에 엔티티명을 작성
        //3. 엔티티명을 작성 후 반드시 별칭을 부여해서 이용
        String jsql="select b from board b";
        //entityManager객체가 제공하는 createQuery메소드로 쿼리문 생성
        //첫번째 매개변수 : JPQL구문을 String타입으로 전달
        //두번째 매개변수(생략) : 반환하는 엔티티타입을 설정
        TypedQuery<BoardEntity> query=em.createQuery(jsql, BoardEntity.class);

        //결과를 가져오기
        //1. getResultList() : 가져온 엔티티를 List로 반환
        //2. getSingleResult() : 한개 엔티티를 가져와 반환
        //3. getResultStream() : 가져온 엔티티를 stream으로 반환
        List<BoardEntity> boardList=query.getResultList();
//        boardList.forEach(System.out::println);
//        System.out.println(boardList.size());

        //원하는 필드를 선택해서 조회하기
        jsql="select b.boardTitle from board b";
        Query query1=em.createQuery(jsql);
        List<Object> titleList=query1.getResultList();
        System.out.println("====== 제목만 출력하기 ======");
        titleList.forEach(System.out::println);

        //원하는 필드 다수로 선택해서 조회하기
        jsql="select b.boardTitle,b.boardContent,b.boardDate from board b";
        query1=em.createQuery(jsql);
        List<Object[]> manycolumnList=query1.getResultList();
        manycolumnList.forEach(row->{
            System.out.println(row[0]+" "+row[1]+" "+row[2]);
        });

        //선택한 컬럼으로 엔티티를 생성해서 반환
        jsql="""
             select 
               NEW com.jpa.jpql.entity.BoardEntity(b.boardTitle,b.boardReadCount,b.boardDate)
             from board b
             """;
        query=em.createQuery(jsql,BoardEntity.class);
        List<BoardEntity> constructorList=query.getResultList();
        System.out.println("====== 생성자로 가져온 데이터 ======");
        constructorList.forEach(System.out::println);

//      query.getSingleResult();//row(엔티티)가 한개만 가져오는것
        query.getResultStream()
                 .filter(boardEntity->boardEntity.getBoardReadCount()>5)
                 .forEach(System.out::println);
    }

    public void useWhere(EntityManager em){
        //JPQL에서 where절 사용하기
        //표준 SQL과 동일하게 사용할 수 있음
        //where절에 대상이 되는 항목은 엔티티의 필드로 설정
        String jsql="select b from board b where b.boardWriter.userId='admin'";
        TypedQuery<BoardEntity> tquery=em.createQuery(jsql,BoardEntity.class);
        System.out.println(tquery.getResultStream().count());
        //변수를 이용해서 where 사용하기
        Scanner sc=new Scanner(System.in);
        String id=sc.nextLine();
        jsql="select b from board b where b.boardWriter.userId='"+id+"'";
        tquery=em.createQuery(jsql,BoardEntity.class);
        tquery.getResultStream().forEach(System.out::println);

    }
}
