package com.jpa.model.entity;


import com.jpa.common.Gender;
import com.jpa.common.Role;
import com.jpa.model.dto.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "memberEntity")
//테이블 관련 설정을 하는 어노테이션
@Table(name = "MEMBER_ENTITY", uniqueConstraints = {@UniqueConstraint(name ="uq_memberId_userName", columnNames={"MEMBER_ID","MEMBERNAME"})
                                })
//sequence객체를 생성하는 어노테이션
@SequenceGenerator(name = "seqMemberEntityNo"
            , sequenceName = "seq_memberentity_no"
            ,allocationSize= 1, initialValue = 1)
public class MemberEntity {
    @Id
//    @Column(name = "member_no")
    @GeneratedValue(generator = "seqMember",strategy = GenerationType.SEQUENCE)
    private Long memberNo;
    @Column(name = "member_id", nullable = false, unique = true)
    private String memberId;

    private String memberPwd;
    @Column(name = "age")
    private int memberAge;

    @Column(length = 20)
    private String memberName;

    @Column(name = "gender")
    @Enumerated(EnumType.ORDINAL) // 번호로 저장
    private Gender memberGender;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Temporal(TemporalType.DATE)
    private Date birthDay;

    @Temporal(TemporalType.TIMESTAMP)
    private Date sessionLog;

    @Embedded
    private Address address;

    @Transient
    private String test;
    @Transient
    private String test2;

    @Transient
    private List<String> test3;

    @Lob//Clob
    private String description;

    @Lob//Blob
    private Byte[] bytes;


    //SQL문으로 컬럼을 만드는 구문을 작성
    @Column(name = "myphone",columnDefinition = "varchar2(20) default '없음' not null ")
    private String phone;


}
