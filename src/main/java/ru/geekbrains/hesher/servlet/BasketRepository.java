package ru.geekbrains.hesher.servlet;

import org.springframework.stereotype.Repository;
import ru.geekbrains.spring.lesson7h.model.dtos.ProductDto;
import ru.geekbrains.spring.lesson7h.model.entities.Basket;

import java.util.List;
import java.util.Optional;

@Repository
public class BasketRepository {

    BasketRepository() {
        Basket.getInstance();
    }

    public ProductDto add(ProductDto product) {
        Basket.add(product);
        return product;
    }

    public void delete(int id) {
        Basket.delete(id);
    }

    public List<ProductDto> findAll() {
        return Basket.getAll();
    };

    public Optional<ProductDto> get(int id) {
        return Basket.get(id);
    };
}
