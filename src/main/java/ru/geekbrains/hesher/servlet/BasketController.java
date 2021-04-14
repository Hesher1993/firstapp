package ru.geekbrains.hesher.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.lesson7h.exceptions.ProductNotFoundException;
import ru.geekbrains.spring.lesson7h.model.dtos.ProductDto;
import ru.geekbrains.spring.lesson7h.services.BasketService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/basket")
public class BasketController {

    @Autowired
    private BasketService basketService;

    @GetMapping
    public List<ProductDto> getAll() {
        return basketService.getAll();
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Integer id) {
        return basketService.getById(id).orElseThrow(() -> new ProductNotFoundException(id.toString()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto add(@RequestBody ProductDto product) {
        return basketService.add(product);
    }

    @DeleteMapping("/{id}")
    public ProductDto delete(@PathVariable Integer id) {
        return basketService.delete(id);
    }
}
