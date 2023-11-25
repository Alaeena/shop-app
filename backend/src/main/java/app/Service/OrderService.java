package app.Service;


import app.Dao.OrderDao;
import app.HttpDto.OrderCreationRequest;
import app.Model.*;
import app.Model.Criteria.OrderPage;
import app.Model.Dto.OrderDto;
import app.Model.Enum.OrderState;
import app.Model.Mapper.OrderMapper;
import app.Repository.AddressRepository;
import app.Repository.OrderItemRepository;
import app.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static app.Model.Enum.CheckoutType.UPFRONT;
import static app.Model.Enum.OrderState.CANCELLED;
import static app.Model.Enum.OrderState.PENDING;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderDao orderDao;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final AddressRepository addressRepository;

    public List<OrderDto> getOrders(OrderPage page, OrderState orderState, Long userId) {
        List<Order> list = orderDao.findProductWithFilter(page, orderState, userId);
        return list.stream()
                .map(OrderMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public Map<String, String> addOrder(UserEntity user, OrderCreationRequest request) {
        Map<Long, List<OrderItem>> map = new HashMap<>();
        List<Shop> shops = new ArrayList<>();

        Address address = addressRepository
                .findByUserAndId(user, request.getAddressId())
                .orElseThrow(() -> new RuntimeException("Invalid address id"));
        for (Long itemId : request.getItems()) {
            OrderItem item = orderItemRepository.findById(itemId)
                    .orElseThrow(() -> new RuntimeException("Invalid item id"));
            Shop shop = item.getShop();
            Long shopId = shop.getId();
            item.setCart(null);

            if (map.get(shopId) == null) {
                List<OrderItem> list = new ArrayList<>();
                list.add(item);

                shops.add(shop);
                map.put(shopId, list);
            } else {
                map.get(shopId).add(item);
            }
        }
        for (Shop shop : shops) {
            List<OrderItem> list = map.get(shop.getId());
            Order order = new Order(
                    request.getCheckoutType(),
                    list,
                    address
            );
            order.setUser(user);
            orderRepository.save(order);
        }

        return request.getCheckoutType() == UPFRONT
                ? Map.of("Redirect:", "/user/checkout")
                : Map.of("Redirect:", "/payment");
    }

    public void cancelOrder(UserEntity user, Long orderId) {
        Order order = orderRepository.findByUserAndId(user, orderId)
                .orElseThrow(() -> new RuntimeException("No order was found!!"));
        order.setState(CANCELLED);
        order.addTimestamp(new Timestamp(CANCELLED));
        orderRepository.saveAndFlush(order);
    }

    public void payOrder(UserEntity user, Long orderId) {
        Order order = orderRepository.findByUserAndId(user, orderId)
                .orElseThrow(() -> new RuntimeException("No order was found!!"));
        order.setState(PENDING);
        order.addTimestamp(new Timestamp(PENDING));
        order.setPaid(true);
        orderRepository.saveAndFlush(order);
    }
}
