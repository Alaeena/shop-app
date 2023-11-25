package app.Model.Mapper;

import app.Model.Dto.OrderItemDto;
import app.Model.OrderItem;

public class OrderItemMapper {
    public static OrderItemDto mapToDto(OrderItem object) {
        return OrderItemDto.builder()
                .id(object.getId())
                .productId(object.getProductId())
                .productUrl(object.getProductUrl())
                .productName(object.getProductName())
                .shopId(object.getShopId())

                .number(object.getNumber())
                .discount(object.getDiscount())
                .price(object.getPrice())
                .total(object.getTotal())
                .build();
    }
}
