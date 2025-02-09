package com.jpa.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

@Entity
@Table(name="product")
@SequenceGenerator(name="seqProuctNo",
        sequenceName = "seq_product_no",allocationSize = 1)
public class ProductEntity {

    @Id
    @GeneratedValue(generator = "seqProuctNo",strategy = GenerationType.SEQUENCE)
    private Long productNo;

    private String productName;

    private Integer price;

    @ManyToMany
    @JoinTable(name="consumer_product",
    joinColumns = @JoinColumn(name="product_ref"),
    inverseJoinColumns = @JoinColumn(name="consumer_ref"))
    private List<ConsumerEntity> consumers=new ArrayList<>();

}
