package ru.geekbrains.hesher.servlet;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String msg) {
        super("Product not found:" + msg);
    }
}
