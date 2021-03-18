package ru.geekbrains.hesher.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.lesson7h.exceptions.InvalidPageException;
import ru.geekbrains.spring.lesson7h.exceptions.ProductErrorResponse;
import ru.geekbrains.spring.lesson7h.exceptions.ProductNotFoundException;
import ru.geekbrains.spring.lesson7h.model.entities.Product;
import ru.geekbrains.spring.lesson7h.model.dtos.ProductDto;
import ru.geekbrains.spring.lesson7h.repositories.specifications.ProductsSpecifications;
import ru.geekbrains.spring.lesson7h.services.ProductService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Page<ProductDto> getAll(
            @RequestParam MultiValueMap<String,String> params,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "") String[] sort) {
        if (page < 1) throw new InvalidPageException(page.toString());
        return productService.getAll(ProductsSpecifications.build(params), page - 1, size, Optional.of(sort));
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Long id) {
        return productService.getById(id).orElseThrow(() -> new ProductNotFoundException(id.toString()));
    }

    @GetMapping("/tobasket/{id}")
    public ProductDto toBasket(@PathVariable Long id) {
        ProductDto p = productService.getById(id).orElseThrow(() -> new ProductNotFoundException(id.toString()));
        p = productService.exchangeWithBasket(p);
        return p;
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