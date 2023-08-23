package com.lemare.stock.dtos;

import com.lemare.stock.models.Category;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class ProductDto {
    @NotBlank
    private String name;
    @NotBlank
    private BigDecimal price;
    @NotBlank
    private LocalDateTime entry;

    private String imgUrl;

    private Set<Category> categories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getEntry() {
        return entry;
    }

    public void setEntry(LocalDateTime entry) {
        this.entry = entry;
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
