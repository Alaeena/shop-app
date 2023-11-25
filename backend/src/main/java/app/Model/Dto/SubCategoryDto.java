package app.Model.Dto;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubCategoryDto {
    @Field(type = FieldType.Keyword, index = false)
    private Long id;
    @Field(type = FieldType.Keyword, index = false)
    private String name;
}
