package ru.geekbrains.hesher.servlet;

import ru.geekbrains.hesher.servlet.mvc.model.Product;
import ru.geekbrains.hesher.servlet.mvc.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public String showAll(Model model,
                          @RequestParam(required = false, name = "min_cost") Double minCost, @RequestParam(required = false, name = "max_cost") Double maxCost){
        model.addAttribute("products", productService.findAll(minCost, maxCost));
        return "products";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id){
        productService.deleteById(id);
        return "redirect:products";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Product p){
        productService.add(p);
        return "redirect:/products";
    }
}
