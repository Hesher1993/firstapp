package ru.geekbrains.hesher.servlet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring.university.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findAllByCostGreaterThan(double minCost);
    public List<Product> findAllByCostLessThan(double maxCost);
    public List<Product> findAllByCostBetween(double minCost, double maxCost);

    public List<Product> findAllByTitleContains(String title);
}
