package app.model.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryAttributeDto {
    private String name;
    private Set<String> attributes;
}
