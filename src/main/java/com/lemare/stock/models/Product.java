package com.lemare.stock.models;


import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name= "tb_products")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private LocalDateTime entry;

    private String imgUrl;
    @ManyToMany
    @JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public Product() {
    }

    public Product(Long id, String name, BigDecimal price, LocalDateTime entry, String imgUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.entry = entry;
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

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

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Category> getCategories() {
        return categories;
    }
}
