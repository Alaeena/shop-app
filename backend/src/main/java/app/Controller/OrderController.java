package app.Controller;


import app.HttpDto.OrderCreationRequest;
import app.Model.Criteria.OrderPage;
import app.Model.Dto.OrderDto;
import app.Model.Enum.OrderState;
import app.Model.UserEntity;
import app.Service.OrderService;
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
            @AuthenticationPrincipal UserEntity user
    ) {
        return Map.of(
                "data",
                orderService.getOrders(page, orderState, user.getId())
        );
    }

    @PostMapping
    public Map<String, Map<String, String>> addOrder(
            @AuthenticationPrincipal UserEntity user,
            @Valid @RequestBody OrderCreationRequest request
    ) {
        return Map.of(
                "data",
                orderService.addOrder(user, request)
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
