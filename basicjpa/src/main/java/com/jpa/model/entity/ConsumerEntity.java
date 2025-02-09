package com.jpa.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

@Entity
@Table(name="consumer")
@SequenceGenerator(name = "seqConsumerNo",
        sequenceName = "seq_consumer_no",allocationSize = 1)
public class ConsumerEntity {

    @Id
    @GeneratedValue(generator = "seqConsumerNo",strategy = GenerationType.SEQUENCE)
    private Long consumerNo;
    @Column(unique = true, nullable = false)
    private String id;

    private String password;

    private String name;

    private String email;

    @ManyToMany(mappedBy="consumers")
    private List<ProductEntity> products=new ArrayList<>();

}
