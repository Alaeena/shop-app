package app.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDto {
    private Long id;
    private Integer number;

    private Long shopId;
    private String productName;
    private String productUrl;
    private Long productId;

    private Integer total;
    private Integer price;
    private Integer discount;
}
