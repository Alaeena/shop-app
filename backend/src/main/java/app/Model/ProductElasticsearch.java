package app.Model;

import app.Model.Dto.AttributeValueDto;
import app.Model.Dto.ThumbnailDto;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "product")
public class ProductElasticsearch {
    @Field(type = FieldType.Long, index = false)
    private Long id;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Integer, index = false)
    private Integer price;

    @Field(type = FieldType.Float, index = false)
    private Float rating;
    @Field(type = FieldType.Integer, index = false)
    private Integer love;
    @Field(type = FieldType.Integer, index = false)
    private Integer sold;
    @Field(type = FieldType.Integer, index = false)
    private Integer quantity;
    @Field(type = FieldType.Integer, index = false)
    private Integer discount;

    @Field(type = FieldType.Text, index = false)
    private String description;
    @Field(type = FieldType.Nested, index = false)
    private List<ThumbnailDto> thumbnails;

    @Field(type = FieldType.Nested)
    private List<AttributeValueDto> specification;
    @Field(type = FieldType.Nested)
    private List<AttributeValueDto> attributes;

    @Field(type = FieldType.Long)
    private Long categoryId;
    @Field(type = FieldType.Text)
    private String categoryName;
    @Field(type = FieldType.Text)
    private String shopName;
}
