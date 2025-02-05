package com.jpa.model.dto;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Address {
    private String sido;
    private String zipCode;
    private String gungu;
    private String dong;
}
