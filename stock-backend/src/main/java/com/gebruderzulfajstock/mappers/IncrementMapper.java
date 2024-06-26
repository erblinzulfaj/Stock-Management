package com.gebruderzulfajstock.mappers;

import com.gebruderzulfajstock.dtos.DiscountDto;
import com.gebruderzulfajstock.dtos.IncrementDto;
import com.gebruderzulfajstock.models.Discount;
import com.gebruderzulfajstock.models.Increment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class IncrementMapper {

    public Increment toEntity(IncrementDto dto) {
        Increment entity = new Increment();
        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        entity.setEmri(dto.getEmri());
        entity.setIncrement(dto.getIncrement());
        entity.setDate(LocalDateTime.now());
        return entity;
    }

    public IncrementDto toDto(Increment entity) {
        IncrementDto dto = new IncrementDto();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setEmri(entity.getEmri());
        dto.setIncrement(entity.getIncrement());
        dto.setDate(entity.getDate());
        return dto;
    }


}

