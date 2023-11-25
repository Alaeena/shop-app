package app.Model.Mapper;

import app.Model.Category;
import app.Model.Dto.SubCategoryDto;

public class SubCategoryMapper {
    public static SubCategoryDto mapToDto(Category category) {
        return SubCategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
