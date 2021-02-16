package ru.geekbrains.hesher.servlet;

import org.springframework.stereotype.Component;
import ru.geekbrains.hesher.servlet.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> productList = new ArrayList<>();

    public ProductRepository() {
    }

    @PostConstruct
    public void initialize(){
        productList.addAll(Arrays.asList(
                new Product(1l, "Product 1", 100),
                new Product(2l, "Product 2", 200),
                new Product(3l, "Product 3", 300),
                new Product(4l, "Product 4", 400),
                new Product(5l, "Product 5", 500)
        ));

    }

    public List<Product> getProductList() {
        return Collections.unmodifiableList(productList);
    }

    public Product getProductById(long id){
        return productList.stream().filter(p->p.getId()==id).findFirst().get();
    }

    public void save(Product product){
        productList.add(product);
    }

    public void deleteById(long id){
        productList.removeIf(p->p.getId()==id);
    }

    public long getNextId(){
        return productList.stream().mapToLong(p-> p.getId()).max().getAsLong()+1l;
    }

    @Override
    public String toString() {

        if(productList.isEmpty()){
            return "Список товаров пуст";
        }

        StringBuilder str = new StringBuilder();
        for (Product p:productList) {
            str.append(p).append("\n");
        }
        return str.toString();
    }
}
