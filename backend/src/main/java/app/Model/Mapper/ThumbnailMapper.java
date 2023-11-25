package app.Model.Mapper;

import app.Model.Dto.ThumbnailDto;
import app.Model.Thumbnail;

public class ThumbnailMapper {
    public static ThumbnailDto mapToDto(Thumbnail thumbnail) {
        return ThumbnailDto.builder()
                .type(thumbnail.getType().toString())
                .url(thumbnail.getUrl())
                .build();
    }
}
