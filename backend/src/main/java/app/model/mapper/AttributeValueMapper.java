package app.model.mapper;

import app.model.dto.AttributeValueDto;
import app.model.postgres.AttributeValue;

public class AttributeValueMapper {
    public static AttributeValueDto mapToDto(AttributeValue object) {
        return AttributeValueDto.builder()
                .id(object.getId())
                .name(object.getName())
                .value(object.getValue())
                .build();
    }
}
