package app.Model.Mapper;

import app.Model.Dto.TimeStampDto;
import app.Model.Timestamp;

public class TimeStampMapper {
    public static TimeStampDto mapToDto(Timestamp timestamp) {
        return TimeStampDto.builder()
                .state(timestamp.getState())
                .time(timestamp.getTime())
                .build();
    }
}
