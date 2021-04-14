package ru.geekbrains.hesher.servlet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "buyers")
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "session_id")
    private String sessionId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "buyer")
    private List<Order> orders;

    @Override
    public String toString() {
        return String.format("Buyer: [%d %s]", id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Buyer customer = (Buyer) o;
        return id == Buyer.id && Objects.equals(name, Buyer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Buyer(String name, User user) {
        if (name.equals(""))
            this.name = user.getLogin();
        else this.name = name;
        this.user = user;
        this.orders = new ArrayList<>();
    }
}