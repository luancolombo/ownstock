package com.lemare.stock.services;

import com.lemare.stock.models.Category;
import com.lemare.stock.repositories.CategoryRepository;
import com.lemare.stock.services.Exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Category saveCategory(Category category) {
        return repository.save(category);
    }
    public List<Category> findAll() {
        return repository.findAll();
    }
    public Category findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
    @Transactional
    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }
}

