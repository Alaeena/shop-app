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
public class CategoryAttributeDto {
    @Field(type = FieldType.Keyword, index = false)
    private String name;
    private Set<String> attributes;
}
