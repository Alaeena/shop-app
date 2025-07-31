package app.model.mapper;

import app.model.dto.ShopDto;
import app.model.postgres.Shop;

public class ShopMapper {
    public static ShopDto mapToDto(Shop object) {
        return ShopDto.builder()
                .createdOn(object.getCreatedOn())
                .name(object.getName())
                .url(object.getUrl())
                .id(object.getId())
                .build();
    }
}
