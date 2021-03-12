package ru.geekbrains.hesher.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.university.model.Product;
import ru.geekbrains.spring.university.services.ProductService;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllByCostBetween(@RequestParam(required = false, name = "minCost") Double minCost,
                                             @RequestParam(required = false, name = "maxCost") Double maxCost)
    { if (minCost==null && maxCost == null) {return productService.getAll();}
        if (minCost==null) {return productService.getAllByCostLessThan(maxCost);}
        if (maxCost==null) {return productService.getAllByCostGreaterThan(minCost);}

        return productService.getAllByCostBetween(minCost, maxCost);}

    @GetMapping("/id:{id}")
    public Product getById(@PathVariable long id){return productService.getById(id);}

    @GetMapping("/title:{title}")
    public List<Product> getProductByTitleContains(@PathVariable String title){return productService.getAllByTitleContains(title);}

    @PostMapping
    public Product save(@RequestBody Product product){return productService.save(product);}

    @GetMapping("/delete/{id}")
    public void deleteById(@PathVariable long id){productService.deleteById(id);}
}
