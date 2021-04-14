package ru.geekbrains.hesher.servlet;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.spring.lesson7h.model.entities.Product;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ProductDto implements Serializable {
    private Long id;
    private String title;
    private Long cost;
    private String category;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.cost = product.getCost();
        this.category = product.getCategory().getTitle();
    }
}
