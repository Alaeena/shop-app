package app.service;

import app.model.dto.CartDto;
import app.model.dto.CartListDto;
import app.model.mapper.CartListMapper;
import app.model.mapper.CartMapper;
import app.model.postgres.*;
import app.repository.postgres.CartRepository;
import app.repository.postgres.OrderItemRepository;
import app.repository.postgres.OrderRepository;
import app.repository.postgres.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    public CartDto getCart(UserEntity user) {
        return CartMapper.mapToDto(user.getCart());
    }

    public CartListDto getCartList(UserEntity user) {
        return CartListMapper.mapToDto(user.getCart());
    }

    public void addItemWithOrderId(UserEntity user, Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("No order found"));
        List<OrderItem> list = new ArrayList<>();
        Cart cart = user.getCart();
        for (OrderItem item : order.getItems()) {
            Optional<OrderItem> optional =
                    orderItemRepository.findByCartIdAndProduct(cart.getId(), item.getProduct());
            OrderItem newItem;
            if (optional.isPresent()) {
                newItem = optional.get();
                newItem.setNumber(item.getNumber() + newItem.getNumber());
            } else {
                newItem = new OrderItem(item);
                cart.addItem(newItem);
            }
            list.add(newItem);
        }
        orderItemRepository.saveAll(list);
    }

    public CartDto addItem(Long productId, String email) {
        Cart cart = cartRepository.findByUserEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid email"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Invalid product id"));
        Optional<OrderItem> optional =
                orderItemRepository.findByCartIdAndProduct(cart.getId(), product);
        if (optional.isPresent()) {
            OrderItem item = optional.get();
            item.setNumber(item.getNumber() + 1);
        } else {
            OrderItem item = new OrderItem(product, 1);
            cart.addItem(item);
        }

        cartRepository.save(cart);
        return CartMapper.mapToDto(cart);
    }

    public CartDto updateItem(UserEntity user, Long itemId, Integer number) {
        OrderItem item = orderItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Invalid item id"));
        if (user.getCart().getId() != item.getCart().getId()) {
            throw new RuntimeException("This item does not belong to the user's cart");
        }

        Cart cart = item.getCart();
        item.setNumber(number);

        cartRepository.save(cart);
        return CartMapper.mapToDto(cart);
    }

    public CartDto deleteItem(UserEntity user, Long itemId) {
        OrderItem item = orderItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Invalid item id"));
        if (user.getCart().getId() != item.getCart().getId()) {
            throw new RuntimeException("This item does not belong to the user's cart");
        }
        Cart cart = item.getCart();
        cart.removeItem(item);

        cartRepository.save(cart);
        return CartMapper.mapToDto(cart);
    }


}
