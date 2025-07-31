package app.controller;


import app.httpDto.OrderCreationRequest;
import app.model.criteria.OrderPage;
import app.model.dto.OrderDto;
import app.model.enums.OrderState;
import app.model.postgres.UserEntity;
import app.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
@PreAuthorize("isAuthenticated()")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public Map<String, List<OrderDto>> getOrder(
            @ModelAttribute OrderPage page,
            @RequestParam("orderState") OrderState orderState,
            @AuthenticationPrincipal UserEntity user,
            HttpServletRequest request
    ) {
        return Map.of(
                "data",
                orderService.getOrders(page, orderState, user.getId())
        );
    }

    @PostMapping
    public Map<String, Map<String, String>> createOrder(
            @AuthenticationPrincipal UserEntity user,
            @Valid @RequestBody OrderCreationRequest request
    ) {
        return Map.of(
                "data",
                orderService.createOrder(user, request)
        );
    }

    @GetMapping("/cancel")
    public void cancelOrder(
            @RequestParam("id") Long orderId,
            @AuthenticationPrincipal UserEntity user) {
        orderService.cancelOrder(user, orderId);
    }

    @GetMapping("/pay")
    public void payOrder(
            @RequestParam("id") Long orderId,
            @AuthenticationPrincipal UserEntity user) {
        orderService.payOrder(user, orderId);
    }
}
