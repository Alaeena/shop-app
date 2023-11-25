package app.Model.Dto;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    @Field(type = FieldType.Long, index = false)
    private Long id;
    @Field(type = FieldType.Keyword)
    private String name;
    @Field(type = FieldType.Nested, index = false)
    private Set<CategoryAttributeDto> attributes;
    @Field(type = FieldType.Nested, index = false)
    private Set<SubCategoryDto> subCategories;
}
