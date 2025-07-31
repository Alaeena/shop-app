package app.utils.vnpay;

import app.model.postgres.Order;
import app.model.postgres.Transaction;
import app.model.postgres.UserOrders;
import app.repository.postgres.OrderRepository;
import app.repository.postgres.UserOrdersPostgresRepository;
import app.repository.postgres.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@Profile("production")
public class VNPayPostgresUtils extends VNPayUtils {
    private final UserOrdersPostgresRepository userOrdersPostgresRepository;

    public VNPayPostgresUtils(UserRepository userRepository, OrderRepository orderRepository, UserOrdersPostgresRepository userOrdersPostgresRepository) {
        super(userRepository, orderRepository);
        this.userOrdersPostgresRepository = userOrdersPostgresRepository;
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
        userOrders.setOrdersFromList(orders);
        userOrdersPostgresRepository.save(userOrders);
    }

    @Override
    public Transaction getTransaction(Map<String, String> fields) {
        String vnp_TxnRef = fields.get("vnp_TxnRef");
        UserOrders userOrders = userOrdersPostgresRepository.findById(vnp_TxnRef)
                .orElse(null);
        if (userOrders == null) {
            log.error("[VNPAYService] Invalid TxnRef");
            return null;
        }
        List<Long> orderIds = Arrays.stream(userOrders.getOrders()).boxed().toList();
        return buildTransaction(fields, userOrders.getUserId(), orderIds);
    }
}
