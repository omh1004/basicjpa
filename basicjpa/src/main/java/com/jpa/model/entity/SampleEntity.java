package com.jpa.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.namespace.QName;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class SampleEntity {
    @Id
    private long id;
    @Column(name="sample_data")
    private String data;
}
