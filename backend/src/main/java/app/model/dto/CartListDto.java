package app.model.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartListDto {
    List<OrderItemDto> cart;
    
}
