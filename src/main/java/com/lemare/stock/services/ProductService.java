package com.lemare.stock.services;

import com.lemare.stock.models.Product;
import com.lemare.stock.repositories.ProductRepository;
import com.lemare.stock.services.Exceptions.ProductNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }
    @Transactional
    public Product saveProduct(Product product) {
        return repository.save(product);
    }
    public List<Product> findAll() {
        return repository.findAll();
    }
    public Product findById(Long id) {
        Optional<Product> product = repository.findById(id);
        return product.orElseThrow(() -> new ProductNotFoundException(id));
    }
    @Transactional
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }
}
