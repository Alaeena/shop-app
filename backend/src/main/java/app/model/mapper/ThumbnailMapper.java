package app.model.mapper;

import app.model.dto.ThumbnailDto;
import app.model.Thumbnail;

public class ThumbnailMapper {
    public static ThumbnailDto mapToDto(Thumbnail thumbnail) {
        return ThumbnailDto.builder()
                .type(thumbnail.getType().toString())
                .url(thumbnail.getUrl())
                .build();
    }
}
