package com.gebruderzulfajstock.dtos;

import com.gebruderzulfajstock.models.enums.Type;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private long id;
    private String type;
    private String origin;
    private String name;
    private String code;
    private String emri;
    private double quantity;
    private LocalDateTime localDateTime;
}
