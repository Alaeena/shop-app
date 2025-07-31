package app.utils.vnpay;

import app.model.postgres.Order;
import app.model.postgres.Transaction;
import app.model.redis.UserOrders;
import app.repository.postgres.OrderRepository;
import app.repository.postgres.UserRepository;
import app.repository.redis.UserOrdersRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
@Profile("dev")
public class VNPayRedisUtils extends VNPayUtils {
    private final UserOrdersRedisRepository userOrdersRedisRepository;

    public VNPayRedisUtils(UserRepository userRepository, OrderRepository orderRepository, UserOrdersRedisRepository userOrdersRedisRepository) {
        super(userRepository, orderRepository);
        this.userOrdersRedisRepository = userOrdersRedisRepository;
    }

    @Override
    public void cacheTransaction(Map<String, String> params, List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return;
        }
        String vnp_TxnRef = params.get("vnp_TxnRef");
        long userId = orders.get(0).getUser().getId();

        UserOrders userOrders = new UserOrders();
        userOrders.setId(vnp_TxnRef);
        userOrders.setUserId(userId);
        for (Order order : orders) {
            userOrders.addOrder(order);
        }
        userOrdersRedisRepository.save(userOrders);
    }

    @Override
    public Transaction getTransaction(Map<String, String> fields) {
        String vnp_TxnRef = fields.get("vnp_TxnRef");
        UserOrders userOrders = userOrdersRedisRepository.findById(vnp_TxnRef)
                .orElse(null);
        if (userOrders == null) {
            log.error("[VNPAYService] Invalid TxnRef");
            return null;
        }
        return buildTransaction(fields, userOrders.getUserId(), userOrders.getOrders());
    }

}
