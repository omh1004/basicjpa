package com.jpa.model.entity;


import com.jpa.common.Gender;
import com.jpa.common.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "memberEntity")
//테이블 관련 설정을 하는 어노테이션
@Table(name = "MEMBER_ENTITY")
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


}
