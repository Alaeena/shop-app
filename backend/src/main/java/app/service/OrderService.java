package app.service;


import app.dao.OrderDao;
import app.httpDto.OrderCreationRequest;
import app.model.criteria.OrderPage;
import app.model.dto.OrderDto;
import app.model.enums.CheckoutType;
import app.model.enums.OrderState;
import app.model.mapper.OrderMapper;
import app.model.postgres.*;
import app.repository.postgres.AddressRepository;
import app.repository.postgres.OrderItemRepository;
import app.repository.postgres.OrderRepository;
import app.utils.vnpay.VNPayUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static app.model.enums.OrderState.CANCELLED;
import static app.model.enums.OrderState.PENDING;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderDao orderDao;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    private final AddressRepository addressRepository;
    private final VNPayUtils vnPayUtils;

    public List<OrderDto> getOrders(OrderPage page, OrderState orderState, Long userId) {
        List<Order> list = orderDao.findProductWithFilter(page, orderState, userId);
        return list.stream()
                .map(OrderMapper::mapToDto)
                .collect(Collectors.toList());
    }

    private String processVnPaymentUrl(Map<Shop, Order> shops) {
        long total = shops.values().stream().mapToInt(Order::getTotal).sum();
        String orderType = shops.keySet().stream().findFirst()
                .get().getShopType().getCode();
        String description = "VNPAY transaction for user: %s";
        List<Order> orders = shops.values().stream().toList();

        Map<String, String> params = vnPayUtils.getVNPayParams(total, description, orderType);
        vnPayUtils.cacheTransaction(params, orders);
        return vnPayUtils.createPaymentUrl(params);
    }

    public Map<String, String> createOrder(UserEntity user, OrderCreationRequest request) {
        Map<Long, List<OrderItem>> map = new HashMap<>();
        Map<Shop, Order> shops = new HashMap<>();
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

                shops.put(shop, new Order());
                map.put(shopId, list);
            } else {
                map.get(shopId).add(item);
            }
        }

        //Save Orders
        for (Shop shop : shops.keySet()) {
            List<OrderItem> list = map.get(shop.getId());
            Order order = new Order(request.getCheckoutType(), list, address);
            order.setUser(user);

            Order savedOrder = orderRepository.save(order);
            shops.put(shop, savedOrder);
        }

        //Redirect URL
        if (request.checkoutType == CheckoutType.VNPAY) {
            String redirectURL = processVnPaymentUrl(shops);
            return Map.of("redirect", redirectURL);
        } else {
            return Map.of("result", "success");
        }
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
