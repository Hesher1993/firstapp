package ru.geekbrains.hesher.servlet;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.lesson7h.exceptions.ProductNotFoundException;
import ru.geekbrains.spring.lesson7h.model.dtos.ProductDto;
import ru.geekbrains.spring.lesson7h.model.entities.Product;
import ru.geekbrains.spring.lesson7h.repositories.ProductRepository;
import ru.geekbrains.spring.lesson7h.repositories.specifications.ProductsSpecifications;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
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

    public Product add(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public Page<ProductDto> getAll(Specification<Product> spec, Integer page,
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
            p = productRepository.findAll(spec, PageRequest.of(page, size, Sort.by(o)));
        } else
            p = productRepository.findAll(PageRequest.of(page, size));
        if (p.getContent().size() > 0)
            return p.map(ProductDto::new);
        else
            throw new ProductNotFoundException("");
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    // TODO Наверняка это делается как-то по-другому
    public ProductDto exchangeWithBasket(ProductDto p) {
        try {
            URL url = new URL("http://localhost:8181/app/api/v1/basket");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            ObjectMapper mapper = new ObjectMapper();
            String jsonInputString = mapper.writeValueAsString(p);

            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                };
                p = mapper.readValue(response.toString(), ProductDto.class);
            }
        } catch (MalformedURLException | ProtocolException e) {
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return p;
    }
}