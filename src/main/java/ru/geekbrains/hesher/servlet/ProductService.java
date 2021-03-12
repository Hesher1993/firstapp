package ru.geekbrains.hesher.servlet;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.lesson7h.controller.SortOrder;
import ru.geekbrains.spring.lesson7h.model.Product;
import ru.geekbrains.spring.lesson7h.repository.ProductRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public Product getById(Long id) {
        return productRepository.findById(id).get();
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

    public List<Product> getAll(Optional<Long> minCost, Optional<Long> maxCost) {
//    public List<Product> getAll(Integer page,
//                                Integer size,
//                                Optional<SortOrder> sortCost,
//                                Optional<SortOrder> sortTitle) {
//        try {
//            if (sortCost.isPresent() )
//                if (sortCost.get() == SortOrder.ASC)
//                    return productRepository.findAll(PageRequest.of(page, size, Sort.by("cost").ascending())).toList();
//                else
//                    return productRepository.findAll(PageRequest.of(page, size, Sort.by("cost").descending())).toList();
//            return productRepository.findAll(PageRequest.of(page, size)).toList();
//        } catch (Exception e) {
//            return List.of(new Product("No data", -1L));
//        }
//
//    }

        public List<Product> getAll(Integer page,
                Integer size,
                Optional<String[]> sort) {
            Page<Product> p = null;
            try {
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
                    return p.toList();
                else
                    return List.of(new Product(p.toString(),1L));
            } catch (IllegalArgumentException e) {
                return List.of(new Product("Invalid page/sort data", -1L));
            } catch (PropertyReferenceException ex) {
                return List.of(new Product("Invalid field/sort definition", -1L));
            }
        }

        public List<Product> getAllByCost(Optional<Long> minCost, Optional<Long> maxCost) {
            if (minCost.isPresent()) {
                if (maxCost.isPresent()) {
                    return productRepository.getAllByCostBetween(minCost.get(),maxCost.get());
                    return productRepository.getAllByCostBetween(minCost.get(), maxCost.get());
                }
                return productRepository.getAllByCostGreaterThanEqual(minCost.get());
            } else if(maxCost.isPresent()) {
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
    }