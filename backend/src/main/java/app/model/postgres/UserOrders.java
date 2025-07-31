package app.model.postgres;

import app.config.ArrayToJsonConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_orders")
public class UserOrders {
    @Id
    private String id;
    private Long userId;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = ArrayToJsonConverter.class)
    private long[] orders = new long[0];

    public void setOrdersFromList(List<Order> orderList) {
        this.orders = orderList.stream()
                .mapToLong(Order::getId)
                .toArray();
    }
}
