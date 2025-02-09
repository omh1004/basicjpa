package com.jpa.model.entity;

import com.jpa.common.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name="student_entity2")
@SequenceGenerator(name="seqStudentNo2",
        sequenceName = "seq_studentno2", initialValue = 10,allocationSize = 1)
public class StudentEntity2 {
    @Id
    @Column(name="student_no")
    @GeneratedValue(generator ="seqStudentNo2" ,strategy = GenerationType.SEQUENCE)
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

    @OneToMany(mappedBy = "student",fetch = FetchType.EAGER)
    private List<StudentSubjectJoinEntity> subjects;

}
