package app.Model.Mapper;

import app.HttpDto.CategoryDetail;
import app.Model.Category;
import app.Model.Dto.CategoryAttributeDto;
import app.Model.Dto.CategoryDto;
import app.Model.Dto.SubCategoryDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CategoryMapper {
    private static CategoryDto mapToNormal(Category object) {
        Set<CategoryAttributeDto> attributes = object.getAttributes().stream()
                .map(CategoryAttributeMapper::mapToDto)
                .collect(Collectors.toSet());
        return CategoryDto.builder()
                .id(object.getId())
                .attributes(attributes)
                .name(object.getName())
                .build();
    }

    public static CategoryDetail mapToDetail(Category object) {
        List<CategoryDto> subCategories = object
                .getSubCategories().stream()
                .map(CategoryMapper::mapToNormal)
                .toList();
        return CategoryDetail.builder()
                .id(object.getId())
                .name(object.getName())
                .subCategories(subCategories)
                .build();
    }


    public static CategoryDto mapToDto(Category object) {
        Category mainCategory = object.getParent() == null
                ? object
                : object.getParent();

        Set<SubCategoryDto> subCategories = mainCategory
                .getSubCategories().stream()
                .map(SubCategoryMapper::mapToDto)
                .collect(Collectors.toSet());

        Set<CategoryAttributeDto> attributes;
        if (object.getParent() == null) {
            attributes = new HashSet<>();
            mainCategory.getSubCategories().forEach(sub -> attributes
                    .addAll(sub.getAttributes()
                            .stream()
                            .map(CategoryAttributeMapper::mapToDto)
                            .collect(Collectors.toSet())
                    ));
        } else {
            attributes = object.getAttributes().stream()
                    .map(CategoryAttributeMapper::mapToDto)
                    .collect(Collectors.toSet());
        }

        return CategoryDto.builder()
                .id(mainCategory.getId())
                .subCategories(subCategories)
                .attributes(attributes)
                .name(mainCategory.getName())
                .build();
    }
}
