package app.model.dto;

import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopOrderDto {
    List<OrderItemDto> cart;
    ShopDto shop;
}
