package app.model.mapper;

import app.model.dto.CartDto;
import app.model.dto.OrderItemDto;
import app.model.dto.ShopDto;
import app.model.dto.ShopOrderDto;
import app.model.postgres.Cart;
import app.model.postgres.OrderItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartMapper {
    public static CartDto mapToDto(Cart object) {
        List<ShopOrderDto> list = new ArrayList<>();
        Map<Long, ShopDto> shopMap = new HashMap<>();
        Map<Long, List<OrderItemDto>> itemMap = new HashMap<>();

        for (OrderItem item : object.getItems()) {
            Long shopId = item.getShopId();
            OrderItemDto itemDto = OrderItemMapper.mapToDto(item);

            if (shopMap.get(shopId) != null) {
                itemMap.get(shopId).add(itemDto);
            } else {
                ShopDto shopDto = ShopMapper.mapToDto(item.getShop());
                List<OrderItemDto> dtoList = new ArrayList<>();
                dtoList.add(itemDto);

                itemMap.put(shopId, dtoList);
                shopMap.put(shopId, shopDto);
            }
        }
        for (Long key : shopMap.keySet()) {
            list.add(new ShopOrderDto(
                    itemMap.get(key),
                    shopMap.get(key)
            ));
        }

        return CartDto.builder()
                .list(list)
                .build();
    }
}
