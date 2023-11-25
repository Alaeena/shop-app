package app.Model.Dto;


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
