package ru.geekbrains.hesher.servlet;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.lesson7h.exceptions.ProductNotFoundException;
import ru.geekbrains.spring.lesson7h.model.dtos.ProductDto;
import ru.geekbrains.spring.lesson7h.model.entities.Product;
import ru.geekbrains.spring.lesson7h.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Optional<ProductDto> getById(Long id) {
        return productRepository.findById(id).map(ProductDto::new);
    }

    public Product getByTitle(String title) {
        return productRepository.findProductByTitle(title);
    }

    public Product add(Product student) {
        return productRepository.save(student);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public Page<ProductDto> getAll(Integer page,
                                   Integer size,
                                   Optional<String[]> sort) {
        Page<Product> p = null;
        if (sort.isPresent()) {
            List o = new ArrayList<Sort.Order>();
            for (int i = 0; i < sort.get().length; i++) {
                String[] s = sort.get()[i].split(",");
                if (s.length >= 2) {
                    o.add(new Sort.Order(Sort.Direction.fromString(s[1]), s[0]));
                } else o.add(new Sort.Order(Sort.DEFAULT_DIRECTION, s[0]));
            }
            p = productRepository.findAll(PageRequest.of(page, size, Sort.by(o)));
        } else
            p = productRepository.findAll(PageRequest.of(page, size));
        if (p.getContent().size() > 0)
            return p.map(ProductDto::new);
        else
            throw new ProductNotFoundException("");
    }

    public List<Product> getAllByCost(Optional<Long> minCost, Optional<Long> maxCost) {
        if (minCost.isPresent()) {
            if (maxCost.isPresent()) {
                return productRepository.getAllByCostBetween(minCost.get(), maxCost.get());
            }
            return productRepository.getAllByCostGreaterThanEqual(minCost.get());
        } else if (maxCost.isPresent()) {
            return productRepository.getAllByCostIsLessThanEqual(maxCost.get());
        } else {
            return productRepository.findAll();
        }
    }

    public List<Product> getAllByNamePart(Optional<String> titlePart) {
        if (titlePart.isPresent())
            return productRepository.getProductByTitleContainsIgnoreCase(titlePart.get());
        else
            return productRepository.findAll();
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }
}