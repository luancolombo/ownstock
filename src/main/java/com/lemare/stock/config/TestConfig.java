package com.lemare.stock.config;

import com.lemare.stock.models.Category;
import com.lemare.stock.models.Product;
import com.lemare.stock.repositories.CategoryRepository;
import com.lemare.stock.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null , "Books");
        Category cat3 = new Category(null , "Computers");

        Product p1 = new Product(null, "Iphone 13 Max Pro", 1450.0, "Loren Something", "https://icenter.pt/cdn/shop/products/iPhone13ProMaxGold-iCenter_600x600.png?v=1649158753");
        Product p2 = new Product(null, "O Senhor dos Anéis", 27.0, "Loren noble", "https://leituria.com/Files/Images/61f54b72-9bb0-47f5-b980-69fb23ec8688.jpeg");
        Product p3 = new Product(null, "MacBook Pro", 2150.0, "Top Devices", "https://www.digitaltrends.com/wp-content/uploads/2021/11/macbook-pro-2021-01.jpg?p=1");

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        productRepository.saveAll(Arrays.asList(p1, p2, p3));

        p1.getCategories().add(cat1);
        p2.getCategories().add(cat2);
        p3.getCategories().add(cat3);

        productRepository.saveAll(Arrays.asList(p1, p2, p3));
    }
}
