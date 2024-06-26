package com.gebruderzulfajstock.models;


import com.gebruderzulfajstock.models.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String origin;
    private String name;
    private String code;
    private String emri;
    private double quantity;
    private LocalDateTime localDateTime;

}
