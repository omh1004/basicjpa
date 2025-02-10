package com.jpa.jpql.controller;

import com.jpa.jpql.entity.BoardEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.w3c.dom.CDATASection;

import java.util.Arrays;
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

        //파라미터를 이용해서 처리하기
        // 1. 인덱스를 값을 넣기 -> ?인덱스번호 : 다음에 인덱스번호 작성
        // 2. 파라미터이름으로 값 넣기 -> :이름

        //쿼리객체에서 제공하는 setParameter(인덱스||이름, 대입할 값)메소드를 이용해서 값을 지정
        jsql = "select b from board b where b.boardWriter.userId= ?1";
        // 인덱스에 값을 넣어서 쿼리를 가져온다.

        TypedQuery<BoardEntity> tqueryParam = em.createQuery(jsql, BoardEntity.class);
      //  tqueryParam.setParameter(1,"abcde");
        tqueryParam.setParameter(1,id);
        Long result  = tqueryParam.getResultList().stream().count();

        System.out.println(result);


        jsql = "select b.boardTitle , b.boardContent from board b where b.boardWriter.userId=:id";

        //결과값이 여러개면 List값으로 나온다.

        Query query2 = em.createQuery(jsql);
        query2.setParameter("id","admin");
        List<Object[]> resultList=query2.getResultList();

        resultList.forEach(datas->{
            System.out.println(Arrays.toString(datas));
        });

     //다수의 컬럼을 이용해서 where 절 을 만들기
     //논리연산자(and,or) 이용하기

        jsql = """
            select 
                b 
            from board b 
            where b.boardNo > :no AND  b.boardWriter.userId= :id
            """;
        tqueryParam = em.createQuery(jsql, BoardEntity.class);
        tqueryParam.setParameter("no",10);
        tqueryParam.setParameter("id","admin");


        tqueryParam.getResultList().forEach(board->{
            System.out.println(board);
        });

        //like 문 작성하기
        //게시글 제목에 첫번째라는 단어가 포함된 게시글 조회하기

        jsql= """
                select b from board b where b.boardTitle like   :content
                """;

        tqueryParam = em.createQuery(jsql, BoardEntity.class);
        // 문구를 직접 만들어 줘야함

        System.out.println("Like문 사용하기 ");

        tqueryParam.setParameter("content" ,"%게시판%");

        jsql= """
                select b from board b where b.boardTitle like   
                '%'||:content ||'%'
                """;
//
//        tqueryParam = em.createQuery(jsql, BoardEntity.class);
//        // 문구를 직접 만들어 줘야함
//        tqueryParam.setParameter("content" ,"%첫번째%");
//
//
        tqueryParam.getResultList().forEach(contents->{
             System.out.println(contents.getBoardTitle());
        });
    }



    public void groupByFunction(EntityManager em){
        // 그룹함수와 group by 절 이용하기
        // 사용자별 게시글 등록수를 출력하기


        String jsql = """
                        select 
                        b.boardWriter.userId 
                        , count(b)
                        , min(b.boardReadCount)
                        ,max(b.boardReadCount)
                        from board b group by b.boardWriter.userId
                        order by 2  desc 
                    """;

        Query query=em.createQuery(jsql);
        List<Object[]> resultList = query.getResultList();
        for(Object[] objects: resultList) {
            System.out.println(Arrays.toString(objects));
        }
    }



    public void paginationTest(EntityManager em ){
        //페이징처리하기
        //setFirstResult(): 시작 row번호 -> 0부터 시작
        //maxResult() : 최대수 -> numPerPage
        String jsql = "select b from board b order by b.boardDate desc" ;
        TypedQuery<BoardEntity> tquery = em.createQuery(jsql,BoardEntity.class);
        //페이징처리하기
        tquery.setFirstResult(0);
        //numPerPage
        tquery.setMaxResults(3);

        List<BoardEntity> resultList= tquery.getResultList();

        System.out.println("페이징처리 결과"+ resultList.size());
        resultList.forEach(System.out::println);
    }

    public void joinTest(EntityManager em){
        //join문 활용하기
        //게시글 회원조회하기
        String jsql = """
                      select 
                        b,m
                       from board b join b.boardWriter m
                """;
        Query query = em.createQuery(jsql);
        List<Object[]> resultList = query.getResultList();
        // 결과값이 Object 배열로 나오므로,
        // stream을 이용해서 배열로 바꾼다음에 출력하도록 함
        resultList.forEach(datas->{
            Arrays.stream(datas).forEach(System.out::println);
        });
        //fetch join -> FetchType.EAGER

        jsql= """
                    select b,m from board b join fetch b.boardWriter m
                """;
        query = em.createQuery(jsql);
        resultList = query.getResultList();
        resultList.forEach(datas1->{
            Arrays.stream(datas1).forEach(System.out::println);
        });

        System.out.println("관계를 설정하지 않은 엔티티 join 하기 ");

        jsql ="""
                select
                b,bc from board b
                left join BoardCommentEntity bc
                on bc.boardRef = b.boardNo""";

        query = em.createQuery(jsql);
        resultList = query.getResultList();
        resultList.forEach(e->{
            Arrays.stream(e).forEach(System.out::println);
        });

    }

}
