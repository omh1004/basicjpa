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
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name="student_entity")
@SequenceGenerator(name="seqStudentNo",
        sequenceName = "seq_studentno", initialValue = 10,allocationSize = 1)
public class StudentEntity {
    @Id
    @Column(name="student_no")
    @GeneratedValue(generator ="seqStudentNo" ,strategy = GenerationType.SEQUENCE)
    private Long studentNo;

    @Column(name="student_name",nullable=false)
    private String studentName;

    @Column(name="student_grade")
    private Integer grade;

    @Column(name="student_classnum")
    private Integer classNum;

    @Column(name="student_gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

//    @Column(name="student_birthday",nullable = false,columnDefinition = "DATE default sysdate")
//    private Date birthday;
//    @Temporal(TemporalType.DATE)
//    private Date birthday=new Date();

//    @Embedded
//    private Address address;

    @OneToOne
    @JoinColumn(name="locker_no",unique = true)
    private LockerEntity locker;

    @ManyToOne
    private ClassRoomEntity classroom;

}
