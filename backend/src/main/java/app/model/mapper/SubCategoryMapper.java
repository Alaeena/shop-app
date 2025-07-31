package app.model.mapper;

import app.model.dto.SubCategoryDto;
import app.model.postgres.Category;

public class SubCategoryMapper {
    public static SubCategoryDto mapToDto(Category category) {
        return SubCategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
