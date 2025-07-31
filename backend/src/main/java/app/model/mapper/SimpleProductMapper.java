package app.model.mapper;

import app.model.dto.SimpleProductDto;
import app.model.postgres.Product;

public class SimpleProductMapper {

    public static SimpleProductDto mapToDTo(Product product) {
        return SimpleProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .url(product.getThumbnails().get(0).getUrl())
                .build();
    }
}
