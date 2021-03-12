package ru.geekbrains.hesher.servlet;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.university.model.Product;
import ru.geekbrains.spring.university.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {return productRepository.findAll();}
    public List<Product> getAllByCostGreaterThan(double minCost){return productRepository.findAllByCostGreaterThan(minCost);}
    public List<Product> getAllByCostLessThan(double maxCost){return productRepository.findAllByCostLessThan(maxCost);}
    public List<Product> getAllByCostBetween(double minCost, double maxCost){return productRepository.findAllByCostBetween(minCost, maxCost);}

    public List<Product> getAllByTitleContains(String title){return productRepository.findAllByTitleContains(title);}

    public Product getById(long id){return productRepository.getOne(id);}

    public Product save(Product product){return productRepository.save(product);}

    public void deleteById(long id){productRepository.deleteById(id);}
}