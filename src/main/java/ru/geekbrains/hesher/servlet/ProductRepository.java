package ru.geekbrains.hesher.servlet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring.lesson7h.model.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductByTitle(String title);

    List<Product> getAllByCostGreaterThanEqual(Long cost);

    List<Product> getAllByCostIsLessThanEqual(Long cost);

    List<Product> getAllByCostBetween(Long cost, Long cost2);

    List<Product> getProductByTitleContainsIgnoreCase(String namePart);
}
