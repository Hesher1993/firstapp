package ru.geekbrains.hesher.servlet;

import ru.geekbrains.hesher.servlet.ResourceNotFoundException;
import ru.geekbrains.hesher.servlet.Product;
import ru.geekbrains.hesher.servlet.ProductService;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping // GET http://localhost:8189/app/products
    public String showAll(Model model,
                          @RequestParam(required = false, name = "minCost") Integer minCost,
                          @RequestParam(required = false, name = "maxCost") Integer maxCost
    ) {
        model.addAttribute("products", productService.findAll(minCost, maxCost));
        return "products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable Long id, HttpServletResponse response) {
        productService.deleteById(id);
        return "redirect:/products"; // [http://localhost:8189/app]/products
    }

    @GetMapping("/select")
    public String selectProductById(Model model, @RequestParam (required = false, name = "id") Long id, HttpServletResponse response) throws ResourceNotFoundException {
        final Optional<Product> product = productService.findById(id);
        if (product.isEmpty()) {
            throw new ResourceNotFoundException(id.toString());
        }
        model.addAttribute("product", product.get());
        return "viewProduct"; // [http://localhost:8189/app]/products
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleException(Model model, ResourceNotFoundException e) {
        model.addAttribute("id", e.getMessage());
        return "productNotFound";

    }


}
