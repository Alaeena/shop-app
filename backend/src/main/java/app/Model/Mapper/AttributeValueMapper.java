package app.Model.Mapper;

import app.Model.AttributeValue;
import app.Model.Dto.AttributeValueDto;

public class AttributeValueMapper {
    public static AttributeValueDto mapToDto(AttributeValue object) {
        return AttributeValueDto.builder()
                .id(object.getId())
                .name(object.getName())
                .value(object.getValue())
                .build();
    }
}
