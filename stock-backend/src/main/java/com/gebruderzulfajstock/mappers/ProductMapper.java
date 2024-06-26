package com.gebruderzulfajstock.mappers;

import com.gebruderzulfajstock.dtos.ProductDto;
import com.gebruderzulfajstock.models.Product;
import com.gebruderzulfajstock.models.enums.Type;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductMapper {

    public Product toEntity(ProductDto dto) {
        Product entity = new Product();
        entity.setId(dto.getId());
        entity.setType(Type.valueOf(dto.getType()));
        entity.setOrigin(dto.getOrigin());
        entity.setName(dto.getName());
        entity.setCode(dto.getCode());
        entity.setEmri(dto.getEmri());
        entity.setQuantity(dto.getQuantity());
        entity.setLocalDateTime(LocalDateTime.now());
        return entity;
    }

    public ProductDto toDto(Product entity) {
        ProductDto dto = new ProductDto();
        dto.setId(entity.getId());
        dto.setType(String.valueOf(entity.getType()));
        dto.setOrigin(entity.getOrigin());
        dto.setName(entity.getName());
        dto.setCode(entity.getCode());
        dto.setEmri(entity.getEmri());
        dto.setQuantity(entity.getQuantity());
        dto.setLocalDateTime(LocalDateTime.now());
        return dto;
    }

}
