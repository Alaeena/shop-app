package app.Model.Dto;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThumbnailDto {
    @Field(type = FieldType.Keyword, index = false)
    private String url;
    @Field(type = FieldType.Keyword, index = false)
    private String type;
}
