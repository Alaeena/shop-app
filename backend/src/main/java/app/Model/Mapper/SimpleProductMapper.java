package app.Model.Mapper;

import app.Model.Dto.SimpleProductDto;
import app.Model.Product;

public class SimpleProductMapper {

    public static SimpleProductDto mapToDTo(Product product) {
        return SimpleProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .url(product.getThumbnails().get(0).getUrl())
                .build();
    }
}
