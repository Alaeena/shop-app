package app.model.mapper;

import app.model.AttributeValue;
import app.model.dto.AttributeValueDto;

public class AttributeValueMapper {
    public static AttributeValueDto mapToDto(AttributeValue object) {
        return AttributeValueDto.builder()
                .id(object.getId())
                .name(object.getName())
                .value(object.getValue())
                .build();
    }
}
