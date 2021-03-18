package ru.geekbrains.hesher.servlet;

import ru.geekbrains.spring.lesson7h.model.dtos.ProductDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Basket {
    private static Basket instance;
    private static List<ProductDto> productList;

    private Basket() {
        productList = new ArrayList<>();
    }

    public static Basket getInstance() {
        if (instance == null) {
            instance = new Basket();
        }
        return instance;
    }

    public static void add(ProductDto product) {
        productList.add(product);
    }

    public static void delete(int id) {
        productList.remove(id);
    }

    public static List<ProductDto> getAll() {
        return productList;
    }

    public static Optional<ProductDto> get(int id) {
        return Optional.of(productList.get(id));
    }
}