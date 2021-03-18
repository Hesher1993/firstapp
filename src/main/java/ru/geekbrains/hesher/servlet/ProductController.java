package ru.geekbrains.hesher.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.lesson7h.exceptions.InvalidPageException;
import ru.geekbrains.spring.lesson7h.exceptions.ProductErrorResponse;
import ru.geekbrains.spring.lesson7h.exceptions.ProductNotFoundException;
import ru.geekbrains.spring.lesson7h.model.entities.Product;
import ru.geekbrains.spring.lesson7h.model.dtos.ProductDto;
import ru.geekbrains.spring.lesson7h.services.ProductService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Page<ProductDto> getAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "") String[] sort) {
        if (page < 1) throw new InvalidPageException(page.toString());
        return productService.getAll(page - 1, size, Optional.of(sort));
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Long id) {
        return productService.getById(id).orElseThrow(() -> new ProductNotFoundException(id.toString()));
    }

    @GetMapping("/title")
    public Product getByName(@RequestParam String title) {
        return productService.getByTitle(title);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product add(@RequestBody Product product) {
        product.setId(null);
        return productService.add(product);
    }

    @PutMapping
    public Product update(@RequestBody Product product) {
        return productService.saveOrUpdate(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(ProductNotFoundException e) {
        ProductErrorResponse per = new ProductErrorResponse();
        per.setStatus(HttpStatus.NOT_FOUND.value());
        per.setMessage(e.getMessage());
        per.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(per,HttpStatus.NOT_FOUND);
    }
}