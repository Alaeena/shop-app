package app.Model.Dto;

import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
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
    private Map<String, String> specification = new HashMap<>();
    private Map<String, String> attributes = new HashMap<>();

    private ShopDto shop;
    private CategoryDto category;
    private String categoryName;
}
