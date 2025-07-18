package app.model.mapper;

import app.model.Category;
import app.model.dto.SubCategoryDto;

public class SubCategoryMapper {
    public static SubCategoryDto mapToDto(Category category) {
        return SubCategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
