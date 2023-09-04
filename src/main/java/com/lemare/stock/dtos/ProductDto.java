package com.lemare.stock.dtos;

import com.lemare.stock.models.Category;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public class ProductDto {
    @NotBlank
    private String name;
    @NotBlank
    private Double price;
    @NotBlank
    private String description;

    private String imgUrl;

    private Set<Category> categories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
