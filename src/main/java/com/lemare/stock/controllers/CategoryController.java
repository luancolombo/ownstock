package com.lemare.stock.controllers;

import com.lemare.stock.dtos.CategoryDto;
import com.lemare.stock.models.Category;
import com.lemare.stock.services.CategoryService;
import com.lemare.stock.services.Exceptions.DatabaseException;
import com.lemare.stock.services.Exceptions.ResourceNotFoundException;
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
@RequestMapping("/categories")
public class CategoryController {

    final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAllCategories() {
        List<Category> list = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        var category = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(category);
    }
    @PostMapping
    public ResponseEntity<Object> newCategory(@RequestBody @Valid CategoryDto categoryDto) {
        var category = new Category();
        BeanUtils.copyProperties(categoryDto, category);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveCategory(category));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryDto categoryDto){
        try {
            var category= service.findById(id);
            category.setName(categoryDto.getName());
            return ResponseEntity.status(HttpStatus.OK).body(service.saveCategory(category));
        }catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable(value = "id") Long id) {
        try {
            var category = service.findById(id);
            service.deleteCategory(id);
            return ResponseEntity.status(HttpStatus.OK).body("Category deleted successfully.");
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
