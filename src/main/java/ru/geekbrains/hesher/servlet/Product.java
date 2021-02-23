package ru.geekbrains.hesher.servlet;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    private Long id;
    private String title;
    private int cost;
}
