package com.gebruderzulfajstock.services.impl;

import com.gebruderzulfajstock.dtos.DiscountDto;
import com.gebruderzulfajstock.dtos.IncrementDto;
import com.gebruderzulfajstock.dtos.ProductDto;
import com.gebruderzulfajstock.exceptions.NotEnoughQuantity;
import com.gebruderzulfajstock.exceptions.ProductNotFound;
import com.gebruderzulfajstock.mappers.DiscountMapper;
import com.gebruderzulfajstock.mappers.IncrementMapper;
import com.gebruderzulfajstock.mappers.ProductMapper;
import com.gebruderzulfajstock.models.Product;
import com.gebruderzulfajstock.models.enums.Type;
import com.gebruderzulfajstock.repositories.DiscountRepository;
import com.gebruderzulfajstock.repositories.IncrementRepository;
import com.gebruderzulfajstock.repositories.ProductRepository;
import com.gebruderzulfajstock.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductMapper mapper;

    @Autowired
    private DiscountMapper discountMapper;

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private IncrementMapper incrementMapper;

    @Autowired
    private IncrementRepository incrementRepository;

    @Override
    public ResponseEntity<String> add(ProductDto add) {
        var product = mapper.toEntity(add);
        repository.save(product);
        return ResponseEntity.ok("Product added successfully");
    }

    @Override
    public ResponseEntity<String> incrementQuantity(long id, double quantityAdded) {
        var optionalEntity = repository.findById(id);
        IncrementDto incrementDto = new IncrementDto();

        if (optionalEntity.isEmpty())
            throw new ProductNotFound("Product not found!");

        var entity = optionalEntity.get();

        double quantity = entity.getQuantity();

        double finalQuantity = quantity + quantityAdded;

        entity.setQuantity(finalQuantity);


        incrementDto.setCode(entity.getCode());
        incrementDto.setEmri(entity.getEmri());
        incrementDto.setIncrement(quantityAdded);
        incrementDto.setDate(LocalDateTime.now());

        addIncrement(incrementDto);

        repository.save(entity);

        return ResponseEntity.ok("Quantity added successfully");
    }

    private void addIncrement(IncrementDto incrementDto) {
        var optional = incrementMapper.toEntity(incrementDto);
        incrementRepository.save(optional);
    }

    @Override
    public ResponseEntity<String> decrementQuantity(long id, double quantityDiscount) {
        var optionalEntity = repository.findById(id);

        DiscountDto discount = new DiscountDto();

        if (optionalEntity.isEmpty())
            throw new ProductNotFound("Product not found!");

        var entity = optionalEntity.get();

        double quantity = entity.getQuantity();

        if (quantity < quantityDiscount) {
            throw new NotEnoughQuantity("Nuk keni sasi te mjaftueshme!");
        } else {
            double finalQuantity = quantity - quantityDiscount;

            entity.setQuantity(finalQuantity);

            discount.setCode(entity.getCode());
            discount.setEmri(entity.getEmri());
            discount.setDiscount(quantityDiscount);
            discount.setDate(LocalDateTime.now());

            addDiscount(discount);

            repository.save(entity);

            return ResponseEntity.ok("Quantity discounted successfully");
        }


    }

    private void addDiscount(DiscountDto dto) {
        var optional = discountMapper.toEntity(dto);
        discountRepository.save(optional);
    }

    @Override
    public ResponseEntity<String> modify(long id, ProductDto modify) {
        var optionalEntity = repository.findById(id);
        if (optionalEntity.isEmpty())
            throw new ProductNotFound("Product not found!");
        var entity = optionalEntity.get();
        entity.setType(Type.valueOf(modify.getType()));
        entity.setOrigin(modify.getOrigin());
        entity.setName(modify.getName());
        entity.setCode(modify.getCode());
        entity.setEmri(modify.getEmri());
        entity.setQuantity(modify.getQuantity());
        repository.save(entity);
        return ResponseEntity.ok("Product updated successfully");
    }

    @Override
    public ResponseEntity<String> removeById(long id) {
        repository.deleteById(id);
        return ResponseEntity.ok("Product removed successfully");
    }

    @Override
    public ProductDto findById(long id) {
        var entity = repository.findById(id);
        if (entity.isEmpty())
            throw new ProductNotFound("Product not found with id: " + id);
        var dto = mapper.toDto(entity.get());
        return dto;
    }

    @Override
    public List<ProductDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }



    @Override
    public List<ProductDto> findAllLessQuantity() {
         List<ProductDto> products = repository.findAll().stream().map(mapper::toDto).toList();

        List<ProductDto> savedProducts = new ArrayList<>();

        for (ProductDto product : products) {
            double quantity = product.getQuantity();
            if (quantity < 100) {
                savedProducts.add(product);
            }
        }
        return savedProducts;
    }

    @Override
    public List<DiscountDto> getDiscountHistory() {
        return discountRepository.findAll().stream().map(discountMapper::toDto).toList();
    }

    @Override
    public List<IncrementDto> getIncrementHistory() {
        return incrementRepository.findAll().stream().map(incrementMapper::toDto).toList();

    }


}
