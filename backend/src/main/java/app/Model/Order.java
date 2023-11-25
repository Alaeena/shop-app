package app.Model;

import app.Model.Enum.CheckoutType;
import app.Model.Enum.OrderState;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static app.Model.Enum.CheckoutType.UPFRONT;
import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_entity")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate createdAt;
    private Boolean paid;

    @Enumerated(EnumType.STRING)
    private OrderState state;
    @Enumerated(EnumType.STRING)
    private CheckoutType checkoutType;

    @OneToMany(mappedBy = "order", cascade = ALL, fetch = LAZY)
    private List<Timestamp> timestamps = new ArrayList<>();
    @OneToMany(mappedBy = "order", cascade = MERGE, fetch = LAZY)
    private List<OrderItem> items = new ArrayList<>();

    @ManyToOne(cascade = MERGE)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne(cascade = MERGE)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(cascade = MERGE)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    public Order(CheckoutType checkoutType, List<OrderItem> items, Address address) {
        this(checkoutType, address);
        this.items = items;
        this.shop = items.get(0).getShop();
        for (OrderItem item : items) {
            item.setOrder(this);
            item.calculate();
        }
    }

    public Order(CheckoutType checkoutType, Address address) {
        this.checkoutType = checkoutType;
        this.address = address;
        this.createdAt = LocalDate.now();

        if (checkoutType == UPFRONT) {
            this.state = OrderState.PENDING;
        } else {
            this.state = OrderState.PAYING;
        }
        for (OrderItem item : items) {
            item.setOrder(this);
        }
        addTimestamp(new Timestamp(this.getState()));
    }

    public void addItem(OrderItem item) {
        this.items.add(item);
        item.setOrder(this);
    }

    public void addTimestamp(Timestamp timestamp) {
        this.timestamps.add(timestamp);
        timestamp.setOrder(this);
    }

    public Integer getTotal() {
        return items.stream().mapToInt(OrderItem::getTotal).sum();
    }
}
