package app.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.FetchType.EAGER;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "cart", fetch = EAGER, cascade = ALL, orphanRemoval = true)
    private Set<OrderItem> items = new HashSet<>();

    @OneToOne(mappedBy = "cart", cascade = MERGE)
    private UserEntity user;

    public void addItem(List<OrderItem> list) {
        list.forEach(this::addItem);
    }

    public void addItem(OrderItem orderItem) {
        items.add(orderItem);
        orderItem.setCart(this);
    }

    public void removeItem(OrderItem orderItem) {
        items.remove(orderItem);
        orderItem.setCart(null);
    }
}
