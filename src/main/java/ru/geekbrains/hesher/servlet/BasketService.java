package ru.geekbrains.hesher.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.lesson7h.model.dtos.ProductDto;
import ru.geekbrains.spring.lesson7h.repositories.BasketRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@Service
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;

    public ProductDto add(ProductDto product) {
        return basketRepository.add(product);
    }

    public ProductDto delete(int id) {
        ProductDto p = basketRepository.get(id).get();
        basketRepository.delete(id);
        return p;
    }

    public List<ProductDto> getAll() {
        return basketRepository.findAll();
    }

    public Optional<ProductDto> getById(int id) {
        return basketRepository.get(id);
    }
}