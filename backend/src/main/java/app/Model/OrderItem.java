package app.Model;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderItem {
    @Id
    @GeneratedValue
    private Long id;

    private Integer number;
    private Integer price;
    private Integer discount;

    @ManyToOne(cascade = MERGE)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(cascade = MERGE, fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(cascade = MERGE, fetch = LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public OrderItem(OrderItem item) {
        this.product = item.getProduct();
        this.number = item.getNumber();
    }

    public OrderItem(Product product, Integer number) {
        this.product = product;
        this.number = number;
    }

    public void calculate() {
        this.price = product.getPrice();
        this.discount = product.getDiscount();
    }

    public Integer getPrice() {
        return price == null ? product.getPrice() : price;
    }

    public Integer getDiscount() {
        return discount == null ? product.getDiscount() : discount;
    }

    public Integer getTotal() {
        Integer value = number * this.getPrice() / 100;
        Integer itemDiscount = (100 - this.getDiscount());
        return value * itemDiscount;
    }

    public Shop getShop() {
        return product.getShop();
    }

    public Long getShopId() {
        return product.getShop().getId();
    }

    public String getProductName() {
        return product.getName();
    }

    public String getProductUrl() {
        return product.getThumbnails().get(0).getUrl();
    }

    public Long getProductId() {
        return product.getId();
    }


}
