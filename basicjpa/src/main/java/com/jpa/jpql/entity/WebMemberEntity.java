package com.jpa.jpql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data

@Entity
@Table(name="MEMBER")
public class WebMemberEntity {
    @Id
    private String userId;
    private String password;
    private String userName;
    private String gender;
    private Integer age;
    private String email;
    private String phone;
    private String address;
    private String hobby;
    private Date enrollDate;

    @ToString.Exclude
    @OneToMany(mappedBy="boardWriter")
    private List<BoardEntity> boardList;

}
