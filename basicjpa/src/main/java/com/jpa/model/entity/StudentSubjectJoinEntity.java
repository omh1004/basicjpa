package com.jpa.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data

@Entity
@Table(name="student_subject_join", uniqueConstraints = {
        @UniqueConstraint(name="uq_student_subject",
                columnNames = {"student_ref","subject_ref"})
})
@SequenceGenerator(name="seqStudentSubjectNo",
        sequenceName = "seq_student_subject_no",allocationSize = 1)
public class StudentSubjectJoinEntity {
    @Id
    @GeneratedValue(generator ="seqStudentSubjectNo",strategy = GenerationType.SEQUENCE)
    private Long studentSubjectNo;

    private String year;
    private String term;

    @ManyToOne
    @JoinColumn(name="student_ref", nullable = false)
    private StudentEntity2 student;

    @ManyToOne
    @JoinColumn(name="subject_ref",nullable = false)
    private SubjectEntity2 subject;



}
