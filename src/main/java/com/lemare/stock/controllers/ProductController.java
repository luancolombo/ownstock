package com.lemare.stock.controllers;

import com.lemare.stock.dtos.ProductDto;
import com.lemare.stock.models.Product;
import com.lemare.stock.services.Exceptions.DatabaseException;
import com.lemare.stock.services.Exceptions.ResourceNotFoundException;
import com.lemare.stock.services.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/products")
public class ProductController {

    final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAllProducts() {
        List<Product> list = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        var product = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }
    @PostMapping
    public ResponseEntity<Object> newProduct(@RequestBody @Valid ProductDto productDto) {
        var product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveProduct(product));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDto productDto){
        try {
            var product = service.findById(id);
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());
            product.setImgUrl(productDto.getImgUrl());
            product.setCategories(productDto.getCategories());

            return ResponseEntity.status(HttpStatus.OK).body(service.saveProduct(product));
        }catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") Long id) {
        try {
            var product = service.findById(id);
            service.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
