package app.model.mapper;

import app.model.dto.OrderDto;
import app.model.dto.OrderItemDto;
import app.model.dto.TimeStampDto;
import app.model.postgres.Order;

import java.util.Set;
import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDto mapToDto(Order order) {
        Set<OrderItemDto> itemDtos = order.getItems().stream()
                .map(OrderItemMapper::mapToDto)
                .collect(Collectors.toSet());
        Set<TimeStampDto> timeStampDtos = order.getTimestamps().stream()
                .map(TimeStampMapper::mapToDto)
                .collect(Collectors.toSet());
        return OrderDto.builder()
                .id(order.getId())
                .total(order.getTotal())
                .paid(order.getPaid())

                .address(order.getAddress())
                .addressType(order.getAddressType())
                .receiver(order.getReceiver())
                .receiverPhone(order.getReceiverPhone())

                .state(order.getState())
                .checkoutType(order.getCheckoutType())
                .createdAt(order.getCreatedAt())
                .shop(ShopMapper.mapToDto(order.getShop()))

                .items(itemDtos)
                .timestamps(timeStampDtos)
                .build();
    }
}
