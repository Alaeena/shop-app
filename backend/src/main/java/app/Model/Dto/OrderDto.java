package app.Model.Dto;

import app.Model.Enum.CheckoutType;
import app.Model.Enum.OrderState;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Integer total;
    private LocalDate createdAt;
    private Boolean paid;

    private OrderState state;
    private CheckoutType checkoutType;
    private AddressDto address;
    private Set<TimeStampDto> timestamps;
    private Set<OrderItemDto> items;
    private ShopDto shop;
}
