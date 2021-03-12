package ru.geekbrains.hesher.servlet;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "cost")
    private Long cost;

    //    public Product(String title, Long cost) {
//        this.title = title;
//        this.cost = cost;
//    }
    public Product(String title, Long cost) {
        this.title = title;
        this.cost = cost;
    }
//
//    public String toString() {
//        return String.format("Product [id = %d, title = %s, cost = %d]", id, title, cost);
//    }
}