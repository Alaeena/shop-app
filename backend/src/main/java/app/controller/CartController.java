package app.controller;

import app.model.dto.CartDto;
import app.model.dto.CartListDto;
import app.model.postgres.UserEntity;
import app.service.CartService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/user/cart")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class CartController {
    private final CartService cartService;

    @GetMapping
    public Map<String, CartDto> getCart(@AuthenticationPrincipal UserEntity user) {
        return Map.of(
                "data",
                cartService.getCart(user)
        );
    }

    @GetMapping("/list")
    public Map<String, CartListDto> getCartList(@AuthenticationPrincipal UserEntity user) {
        return Map.of(
                "data",
                cartService.getCartList(user)
        );
    }

    @PostMapping("/add")
    public void addItemWithOrderId(
            @AuthenticationPrincipal UserEntity user,
            @PathParam("orderId") Long orderId
    ) {
        cartService.addItemWithOrderId(user, orderId);
    }

    @PostMapping
    public Map<String, CartDto> addItem(
            @PathParam("productId") Long productId,
            Principal principal
    ) {
        String email = principal.getName();
        return Map.of(
                "data",
                cartService.addItem(productId, email)
        );
    }

    @PutMapping
    public Map<String, CartDto> updateItem(
            @AuthenticationPrincipal UserEntity user,
            @PathParam("itemId") Long itemId,
            @PathParam("number") Integer number
    ) {
        return Map.of(
                "data",
                cartService.updateItem(user, itemId, number)
        );
    }

    @DeleteMapping
    public Map<String, CartDto> deleteItem(
            @AuthenticationPrincipal UserEntity user,
            @PathParam("itemId") Long itemId
    ) {
        return Map.of(
                "data",
                cartService.deleteItem(user, itemId)
        );
    }
}
