package com.lemare.stock.dtos;

import com.lemare.stock.models.Product;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public class CategoryDto {
    @NotBlank
    private String name;

    private Set<Product> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
