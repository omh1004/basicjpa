package com.jpa.jpql.controller;

import com.jpa.jpql.entity.BoardCommentEntity;
import com.jpa.jpql.entity.BoardEntity;
import com.jpa.model.entity.MemberEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CriteriaController {

    public void basicCriteria(EntityManager em){
        //빌더생성하기
        CriteriaBuilder criteriaBuilder=em.getCriteriaBuilder();

        //쿼리문생성 -> 반환타입을 지정
        CriteriaQuery<BoardEntity> criteriaQuery
                =criteriaBuilder.createQuery(BoardEntity.class);

        //조회대상 entity를 설정
        Root<BoardEntity> root=criteriaQuery.from(BoardEntity.class);

        //select문 작성
        criteriaQuery.select(root);//전체 조회

        //select b from board b

        TypedQuery<BoardEntity> tquery=em.createQuery(criteriaQuery);
        for (BoardEntity boardEntity : tquery.getResultList()) {
            System.out.println(boardEntity);
        }

        //반환되는 타입이 정해지지 않았을때
        //Object
        CriteriaQuery<Object> objectCriteriaQuery=criteriaBuilder.createQuery(Object.class);

        Root<BoardEntity> root2=objectCriteriaQuery.from(BoardEntity.class);

        objectCriteriaQuery.select(root2.get("boardTitle"));

        Query query=em.createQuery(objectCriteriaQuery);

        query.getResultList().forEach(System.out::println);


        //다수컬럼 선택하기
        //Object[]로 반환함.
        //게시글 제목, 게시글 내용 출력하기

        CriteriaQuery<Object[]> objectArrCriteriaQuery
                =criteriaBuilder.createQuery(Object[].class);
        Root<BoardEntity> root3=objectArrCriteriaQuery.from(BoardEntity.class);
        //다수컬럼을 지정하려면 multiSelect()메소드를 이용
        objectArrCriteriaQuery.multiselect(root3.get("boardTitle"),root3.get("boardContent"));

        TypedQuery<Object[]> query2=em.createQuery(objectArrCriteriaQuery);
        query2.getResultList().forEach(arr->{
            System.out.println(Arrays.toString(arr));
        });


        //select메소드를 이용해서 다수의 컬럼을 지정하기
        //CriteriaBuilder객체가 제공하는 array()메소드를 이용하면
        // select로 다수 컬럼을 지정할 수 있다.
        Root<BoardEntity> root4=objectArrCriteriaQuery.from(BoardEntity.class);
        objectArrCriteriaQuery.select(
                criteriaBuilder.array(root4.get("boardReadCount"),root4.get("boardWriter"))
        );
        query2=em.createQuery(objectArrCriteriaQuery);
        query2.getResultList().forEach(b->{
            System.out.println(b[0]+" "+b[1]);
        });
    }

    public void criteriaWhere(EntityManager em){
        //where절 사용하기
        //criteriabuilder객체가 제공하는 where절 함수를 이용
        //대소비교 : greaterThan(), greaterThanOrEqualsTo(), lessThan(), lessThanEqualsTo()
        //숫자형 대소비교 : gt(), lt(), ge(), le()
        //동등비교 : equal(), like(), in()
        //논리연산 : and(), or()
        CriteriaBuilder cb=em.getCriteriaBuilder();

        CriteriaQuery<BoardEntity> criteriaQuery=cb.createQuery(BoardEntity.class);
        Root<BoardEntity> board=criteriaQuery.from(BoardEntity.class);
        criteriaQuery.select(board);

        //where절 작성하기
        Predicate where1=cb.equal(board.get("boardWriter").get("userId"),"admin");
        //criteriaQuery.where(where1);
        Predicate where2=cb.greaterThan(board.get("boardDate"),
                Date.valueOf(LocalDate.of(2019,04,01)));
        //criteriaQuery.where(where2);
        Predicate where3=cb.gt(board.get("boardReadCount"),10);
        //criteriaQuery.where(where3);
        //논리연산 메소드 사용하기
//        Predicate totalwhere=cb.and(where1,where2,where3);
        Predicate totalwhere=cb.or(where1,where2,where3);
        criteriaQuery.where(totalwhere);


        //select b from board b where b.board.userId='admin'
        TypedQuery<BoardEntity> tquery=em.createQuery(criteriaQuery);
        tquery.getResultList().forEach(System.out::println);

        //파라미터로 데이터 처리하기
        //criteriabuilder.parameter(자료형(000.class),"key") 메소드를 이용
        CriteriaQuery<BoardEntity> criteriaQuery1=cb.createQuery(BoardEntity.class);
        Root<BoardEntity> board2=criteriaQuery1.from(BoardEntity.class);
        Predicate likeWhere=cb.like(board2.get("boardTitle"),
                cb.parameter(String.class,"title"));
        // like :title

        criteriaQuery1.select(board2);
        criteriaQuery1.where(likeWhere);

        tquery=em.createQuery(criteriaQuery1);
        tquery.setParameter("title","%안녕%");
        tquery.getResultList().forEach(System.out::println);
    }

    public void orderTest(EntityManager em){
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<BoardEntity> criteriaQuery=cb.createQuery(BoardEntity.class);
        Root<BoardEntity> board=criteriaQuery.from(BoardEntity.class);
        criteriaQuery.select(board);
        //정렬처리하기
//        criteriaQuery.orderBy(cb.asc(board.get("boardDate")));
        criteriaQuery.orderBy(cb.desc(board.get("boardDate")));

        TypedQuery<BoardEntity> tquery=em.createQuery(criteriaQuery);
        tquery.getResultList().forEach(System.out::println);
    }

    public void joinTest(EntityManager em){
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<BoardEntity> criteriaQuery=cb.createQuery(BoardEntity.class);
        Root<BoardEntity> board=criteriaQuery.from(BoardEntity.class);
        //Root.join메소드를 이용해서
        Join<BoardEntity, MemberEntity> join=board.join("boardWriter", JoinType.INNER);
//        CriteriaQuery<BoardCommentEntity> boardCommentEntityCriteriaQuery
//                =cb.createQuery(BoardCommentEntity.class);
//        Root<BoardCommentEntity> boardComment
//                =boardCommentEntityCriteriaQuery.from(BoardCommentEntity.class);

        Join<BoardEntity, BoardCommentEntity> join2=board.join("commentList",
                JoinType.LEFT);
//        join2.on(cb.equal(board.get("boardNo"),boardComment.get("boardRef")));


        criteriaQuery.where(cb.equal(board.get("boardNo"),25));

        em.createQuery(criteriaQuery)
                .getResultList().forEach(System.out::println);

    }

    //동적쿼리문 작성하기
    //데이터가 넘어오는대로 조건문 동적으로 만들어서 조회
    public void dynamicQuery(EntityManager em, Map<String,String> data){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<BoardEntity> criteriaQuery
                = criteriaBuilder.createQuery(BoardEntity.class);
        Root<BoardEntity> boardEntityRoot = criteriaQuery.from(BoardEntity.class);

        //제목, 작성자, 내용
        List<Predicate> where = new ArrayList<>();

        // 동적으로 where 절 만들기
        if(data.get("title")!=null) {
            where.add(criteriaBuilder
                    .like(boardEntityRoot.get("boardTitle")
                            ,criteriaBuilder.parameter(String.class,"title")));
        }
        if(data.get("writer")!=null){
            where.add(criteriaBuilder
                    .equal(boardEntityRoot.get("boardWriter").get("userId")
                            ,criteriaBuilder.parameter(String.class,"writer")));
        }

        if(data.get("content")!=null){
            where.add(criteriaBuilder
                    .like(boardEntityRoot.get("boardContent")
                            ,criteriaBuilder.parameter(String.class,"content")));
        }

        //Object 타입을 Predicate로 형변환 하여 return 한다.
        criteriaQuery.where(criteriaBuilder.and(where.toArray(new Predicate[0])));

        TypedQuery<BoardEntity> query =  em.createQuery(criteriaQuery);

        if(data.get("title")!=null) {
            query.setParameter("title","%"+data.get("title")+"%");
        }
        if(data.get("writer")!=null){
            query.setParameter("writer",data.get("writer"));
        }
        if(data.get("content")!=null){
            query.setParameter("content","%"+data.get("content")+"%");
        }

        query.getResultList().forEach(System.out::println);

    }



}
