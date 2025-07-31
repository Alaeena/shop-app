package app.model.redis;

import app.model.postgres.Order;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(timeToLive = 900)
public class UserOrders {
    @Id
    private String id;
    private Long userId;
    private List<Long> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order.getId());
    }
}
