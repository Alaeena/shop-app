package app.Model.Mapper;

import app.Model.AttributeValue;
import app.Model.Dto.AttributeValueDto;
import app.Model.Dto.ProductDto;
import app.Model.Dto.ThumbnailDto;
import app.Model.Product;
import app.Model.ProductElasticsearch;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductMapper {
    public static ProductElasticsearch mapToElastic(Product product) {
        List<AttributeValueDto> specification = product
                .getSpecification().stream()
                .map(AttributeValueMapper::mapToDto)
                .toList();
        List<AttributeValueDto> attribute = product
                .getAttributes().stream()
                .map(AttributeValueMapper::mapToDto)
                .toList();
        List<ThumbnailDto> thumbnails = product
                .getThumbnails().stream()
                .map(ThumbnailMapper::mapToDto)
                .collect(Collectors.toList());
        return ProductElasticsearch.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())

                .rating(product.getRating())
                .love(product.getLove())
                .sold(product.getSold())
                .quantity(product.getQuantity())
                .discount(product.getDiscount())

                .specification(specification)
                .thumbnails(thumbnails)
                .attributes(attribute)

                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .shopName(product.getShop().getName())
                .build();
    }

    public static ProductDto mapToDto(Product product) {
        Map<String, String> specification = product
                .getSpecification().stream()
                .collect(Collectors.toMap(AttributeValue::getName, AttributeValue::getValue));
        Map<String, String> attribute = product
                .getAttributes().stream()
                .collect(Collectors.toMap(AttributeValue::getName, AttributeValue::getValue));
        List<ThumbnailDto> thumbnails = product
                .getThumbnails().stream()
                .map(ThumbnailMapper::mapToDto)
                .collect(Collectors.toList());
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())

                .rating(product.getRating())
                .love(product.getLove())
                .sold(product.getSold())
                .quantity(product.getQuantity())
                .discount(product.getDiscount())

                .specification(specification)
                .thumbnails(thumbnails)
                .attributes(attribute)

                .category(CategoryMapper.mapToDto(product.getCategory()))
                .categoryName(product.getCategory().getName())
                .shop(ShopMapper.mapToDto(product.getShop()))
                .build();
    }
}
