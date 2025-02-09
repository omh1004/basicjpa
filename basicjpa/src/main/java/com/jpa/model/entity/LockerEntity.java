package com.jpa.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name="locker")
@SequenceGenerator(name="seqLockerNo",
        sequenceName = "seq_locker_no", initialValue = 1,
        allocationSize = 1)
public class LockerEntity {
    @Id
    @Column(name="locker_no")
    @GeneratedValue(generator = "seqLockerNo",
            strategy = GenerationType.SEQUENCE)
    private Long lockerNo;
    private String color;
    @Column(name="locker_level")
    private String level;
    @Column(name="locker_size")
    private String size;
}
