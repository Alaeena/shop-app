package app.model.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    private Long id;
    private String name;
    private Set<CategoryAttributeDto> attributes;
    private Set<SubCategoryDto> subCategories;
}
