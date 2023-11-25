package app.Model.Mapper;

import app.Model.Dto.OrderDto;
import app.Model.Dto.OrderItemDto;
import app.Model.Dto.TimeStampDto;
import app.Model.Order;

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
                .state(order.getState())
                .checkoutType(order.getCheckoutType())
                .createdAt(order.getCreatedAt())
                .shop(ShopMapper.mapToDto(order.getShop()))
                .address(AddressMapper.mapToDto(order.getAddress()))
                .items(itemDtos)
                .timestamps(timeStampDtos)
                .build();
    }
}
