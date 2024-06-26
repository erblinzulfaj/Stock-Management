package com.gebruderzulfajstock.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Increment-History")

public class Increment {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String code;
        private String emri;
        private double increment;
        private LocalDateTime date;


}
