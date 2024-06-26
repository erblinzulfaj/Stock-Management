package com.gebruderzulfajstock.services;

import com.gebruderzulfajstock.dtos.DiscountDto;
import com.gebruderzulfajstock.dtos.IncrementDto;
import com.gebruderzulfajstock.dtos.ProductDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    ResponseEntity<String> add(ProductDto add);
    ResponseEntity<String> incrementQuantity(long id, double quantityAdded);
    ResponseEntity<String> decrementQuantity(long id, double quantityDiscount);
    ResponseEntity<String> modify(long id, ProductDto modify);
    ResponseEntity<String> removeById(long id);

    ProductDto findById(long id);

    List<ProductDto> findAll();

    List<ProductDto> findAllLessQuantity();
    List<DiscountDto> getDiscountHistory();
    List<IncrementDto> getIncrementHistory();
}
