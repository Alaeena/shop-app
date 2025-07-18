package app.model;

import app.model.dto.AttributeValueDto;
import app.model.dto.ThumbnailDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductElasticsearch {
    private Long id;
    private String name;
    private Integer price;

    private Float rating;
    private Integer love;
    private Integer sold;
    private Integer quantity;
    private Integer discount;

    private String description;
    private List<ThumbnailDto> thumbnails;

    private List<AttributeValueDto> specification;
    private List<AttributeValueDto> attributes;

    private Long categoryId;
    private String categoryName;
    private String shopName;
}
