package app.model.mapper;

import app.model.CategoryAttribute;
import app.model.dto.CategoryAttributeDto;

import java.util.Set;
import java.util.stream.Collectors;

public class CategoryAttributeMapper {
    public static CategoryAttributeDto mapToDto(CategoryAttribute object) {
        Set<String> values = object.getAttributes().stream()
                .map(item -> item.getValue())
                .collect(Collectors.toSet());
        return CategoryAttributeDto.builder()
                .attributes(values)
                .name(object.getName())
                .build();
    }


}
