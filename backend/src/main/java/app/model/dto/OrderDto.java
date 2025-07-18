package app.model.dto;

import app.model.enums.AddressType;
import app.model.enums.CheckoutType;
import app.model.enums.OrderState;
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

    private AddressType addressType;
    private String address;
    private String receiver;
    private String receiverPhone;


    private OrderState state;
    private CheckoutType checkoutType;
    private Set<TimeStampDto> timestamps;
    private Set<OrderItemDto> items;
    private ShopDto shop;
}
