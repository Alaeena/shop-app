package app.Model.Mapper;

import app.Model.Dto.ShopDto;
import app.Model.Shop;

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
