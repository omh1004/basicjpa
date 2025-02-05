package com.jpa.model.entity;

import com.jpa.common.Gender;
import com.jpa.model.dto.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SequenceGenerator(name = "seqMemberEntityNo"
        , sequenceName = "seq_studentno"
        ,allocationSize= 1, initialValue = 10)
@Table(name ="student_entity")
public class StudentEntity {

    @Id
    @GeneratedValue(generator = "seqMemberEntityNo",strategy = GenerationType.SEQUENCE)
    private Long studentNo;
    @Column(name = "student_studentname", nullable = false)
    private String studentName;
    @Column(name="student_grade", nullable = false)
    private Integer grade;
    @Column(name = "student_classNum")
    private Integer classNum;
//    @Column(columnDefinition = "Date default sysdate")
    @Temporal(TemporalType.DATE)
    private Date birthDay = new Date();
    @Column(name = "student_gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    //@Column(name = "student_address")
    @Embedded
    private Address address;



}
