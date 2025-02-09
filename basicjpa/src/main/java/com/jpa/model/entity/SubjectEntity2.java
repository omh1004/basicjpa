package com.jpa.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data

@Entity
@Table(name="subject_entity2")
@SequenceGenerator(name="seqSubjectNo2",
        sequenceName = "seq_subject_no2"
        ,allocationSize = 1)
public class SubjectEntity2 {
    @Id
    @GeneratedValue(generator = "seqSubjectNo2",
            strategy = GenerationType.SEQUENCE)
    private Long subjectNo;

    private String subjectName;

    private int subjectFee;

    @OneToMany(mappedBy="subject")
    private List<StudentSubjectJoinEntity> students;



}
