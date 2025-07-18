package app.model.mapper;

import app.model.dto.TimeStampDto;
import app.model.Timestamp;

public class TimeStampMapper {
    public static TimeStampDto mapToDto(Timestamp timestamp) {
        return TimeStampDto.builder()
                .state(timestamp.getState())
                .time(timestamp.getTime())
                .build();
    }
}
