package com.jpa.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

@Entity
@Table(name="subject")
@SequenceGenerator(name="seqSubjectNo",
        sequenceName = "seq_subject_no"
,allocationSize = 1)
public class SubjectEntity {
    @Id
    @GeneratedValue(generator = "seqSubjectNo",
            strategy = GenerationType.SEQUENCE)
    private Long subjectNo;

    private String subjectName;

    private int subjectFee;

    @ManyToMany
    //name : 테이블 이름,
    //joinColumns : this entity의 fk로 참조할 컬럼설정 -> Id
    //inverseJoinColumns : 상대방 entity의 fk로 참조할 컬럼설정 -> Id
    @JoinTable(name="subject_student",
            joinColumns=@JoinColumn(name="subject_ref"),
            inverseJoinColumns=@JoinColumn(name="student_ref"))
    private List<StudentEntity> studentList=new ArrayList<>();

    public void setStudentList(List<StudentEntity> students) {
        if (students != null && students.size() > 0) {
            if (studentList.size() > 0) {
//            studentList.forEach(student->{
//              student.getSubjectList().remove(this);
//            });
                //기존에 등록된 학생엔티티에서 과목엔티티을 삭제
                for (StudentEntity student : studentList) {
                    student.getSubjectList().remove(this);
                }
            }
            students.forEach(student -> {
                //상대방 엔티티에 과목을 추가
                student.getSubjectList().add(
            });
            this.studentList = students;
        } else {
            throw new IllegalArgumentException("잘못된 매개변수가 전달됨");
        }
    }
}
