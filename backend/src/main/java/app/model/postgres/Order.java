package app.model.postgres;

import app.model.enums.AddressType;
import app.model.enums.CheckoutType;
import app.model.enums.OrderState;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static app.model.enums.CheckoutType.UPFRONT;
import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
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
    private AddressType addressType;
    @NonNull
    private String address;
    @NonNull
    private String receiver;
    @NonNull
    private String receiverPhone;

    @Enumerated(EnumType.STRING)
    private OrderState state;
    @Enumerated(EnumType.STRING)
    private CheckoutType checkoutType;

    @OneToMany(mappedBy = "order", cascade = ALL, fetch = LAZY)
    private List<Timestamp> timestamps = new ArrayList<>();
    @OneToMany(mappedBy = "order", cascade = MERGE, fetch = LAZY)
    private List<OrderItem> items = new ArrayList<>();

    @ManyToOne(cascade = MERGE)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(cascade = MERGE)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne(cascade = MERGE)
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

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

        this.address = address.getAddress();
        this.addressType = address.getAddressType();
        this.receiver = address.getReceiver();
        this.receiverPhone = address.getPhone();

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

    public void addTimestamp(Timestamp timestamp) {
        this.timestamps.add(timestamp);
        timestamp.setOrder(this);
    }

    public Integer getTotal() {
        return items.stream().mapToInt(OrderItem::getTotal).sum();
    }
}
