package ru.geekbrains.hesher.servlet;

import ru.geekbrains.hesher.servlet.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1L, "Product 1", 25));
        products.add(new Product(2L, "Product 2", 15));
        products.add(new Product(3L, "Product 3", 25));
        products.add(new Product(4L, "Product 4", 22));
    }

    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(products);
    }

    public void save(Product product) {
        products.add(product);
    }

    public void deleteById(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }
