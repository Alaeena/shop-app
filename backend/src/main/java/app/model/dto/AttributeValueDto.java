package app.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttributeValueDto {
    private Long id;
    private String name;
    private String value;
}
