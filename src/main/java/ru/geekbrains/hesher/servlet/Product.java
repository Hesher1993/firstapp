package ru.geekbrains.hesher.servlet;

public class Product {
import javax.persistence .*;
import java.io.Serializable;

    @Entity
    @Table(name = "product")
    public class Product implements Serializable {

        private static final long serialVersionUID = -397775056591704824L;

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "title")
        private String title;

        @Column(name = "price")
        private Integer price;

        public Product() {
        }

        public Product(String title, Integer price) {
            this.title = title;
            this.price = price;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Product{");
            sb.append("id=").append(id);
            sb.append(", title='").append(title).append('\'');
            sb.append(", price=").append(price);
            sb.append('}');
            return sb.toString();
        }
    }
}