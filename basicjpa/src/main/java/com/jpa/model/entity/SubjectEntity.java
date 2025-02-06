package com.jpa.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

@Data

@Entity
@Table(name="subject")
@SequenceGenerator(name = "seqSubjectNo", sequenceName ="seq_subject_no",allocationSize = 1)
public class SubjectEntity {
    @Id
    @GeneratedValue(generator = "seqSubjectNo",strategy = GenerationType.SEQUENCE)
    private long subjectNo;

    private String subjectName;

    private int subjectFee;

    @ManyToMany
    //name: 테이블 이름
    //joinColumns : this entity의 fk로 참조할 컬럼설정 ->Id
    //invalseJoinColumns : 상대방entity의  fk 로 참조할 컬럼설정 ->Id
    @JoinTable( name="subject_student" ,joinColumns =@JoinColumn(name = "subject_ref")
            ,inverseJoinColumns = @JoinColumn(name = "student_ref"))
    private List<StudentEntity> studentList= new ArrayList<>();

    public void setStudentList(List<StudentEntity> students) {

        if (students != null) {
            if (studentList.size() > 0&&students.size()>0) {
                //기존에 등록된 학생엔티티에서 과목엔티티를 삭제
                studentList.forEach(student -> {
                    student.getSubjectList().remove(this);
                });
            }
            students.forEach(student -> {
                //상대방 엔
                student.getSubjectList().add(this);
            });
            this.studentList = students;
        }else{
            throw new IllegalArgumentException("잘못된매개변수");
        }
    }
}
