package app.model.postgres;

import app.model.enums.ShopType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Shop {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String url;

    @Enumerated(EnumType.STRING)
    private ShopType shopType;

    @Column(name = "created_on")
    private LocalDate createdOn;

    @OneToOne(mappedBy = "shop", cascade = MERGE, fetch = LAZY)
    private UserEntity user;

    @OneToMany(mappedBy = "shop", cascade = MERGE, fetch = LAZY)
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "shop", cascade = MERGE, fetch = LAZY)
    private Set<Product> products = new HashSet<>();

    public Shop(String name, String url, ShopType shopType) {
        this.name = name;
        this.url = url;
        this.shopType = shopType;
        this.createdOn = LocalDate.now();
    }

    public void addProduct(List<Product> list) {
        list.forEach(this::addProduct);
    }

    public void addProduct(Product product) {
        this.products.add(product);
        product.setShop(this);
    }

    public void addOrder(Order order) {
        this.orders.add(order);
        order.setShop(this);
    }
}
