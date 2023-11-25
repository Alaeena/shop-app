package app.Model.Mapper;

import app.Model.Cart;
import app.Model.Dto.CartListDto;
import app.Model.Dto.OrderItemDto;

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
