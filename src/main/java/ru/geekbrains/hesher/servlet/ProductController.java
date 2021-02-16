package ru.geekbrains.hesher.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.hesher.servlet.Product;
import ru.geekbrains.hesher.servlet.ProductService;


import java.util.List;

@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product_list")
    public String productList(Model model){
        List<Product> list = productService.getProductList();
        model.addAttribute("productlist",list);
        return "product_list";
    }

    @GetMapping("/delete_product/{id}")
    public String deleteProduct(@PathVariable long id){
        productService.deleteById(id);
        return "redirect:/product_list";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@RequestParam String title, @RequestParam int cost) {

        Product p = new Product(productService.getNextId(), title, cost);
        productService.save(p);

        return "redirect:/product_list";
    }
}
