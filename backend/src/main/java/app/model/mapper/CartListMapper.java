package app.model.mapper;

import app.model.Cart;
import app.model.dto.CartListDto;
import app.model.dto.OrderItemDto;

import java.util.List;
import java.util.stream.Collectors;

public class CartListMapper {

    public static CartListDto mapToDto(Cart object) {
        List<OrderItemDto> list = object
                .getItems().stream()
                .map(OrderItemMapper::mapToDto)
                .collect(Collectors.toList());
        return CartListDto.builder()
                .cart(list)
                .build();
    }
}
