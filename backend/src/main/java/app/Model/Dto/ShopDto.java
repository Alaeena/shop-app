package app.Model.Dto;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopDto {
    @Field(type = FieldType.Long, index = false)
    private Long id;
    @Field(type = FieldType.Date, index = false)
    private LocalDate createdOn;
    @Field(type = FieldType.Keyword)
    private String name;
    @Field(type = FieldType.Keyword, index = false)
    private String url;
}
