package com.jpa.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name="classroom")
@SequenceGenerator(name="seqclassroom",
        sequenceName = "seq_classroom_no",
        allocationSize=1,initialValue=1)
public class ClassRoomEntity {
    @Id
    @GeneratedValue(generator = "seqclassroom",strategy = GenerationType.SEQUENCE)
    private Long classroomNo;

    private String classroomName;

    private String classroomLevel;
    //단방향 설정
    @ToString.Exclude
    @OneToMany(mappedBy="classroom")//양방향 설정
    private List<StudentEntity> students;
}
