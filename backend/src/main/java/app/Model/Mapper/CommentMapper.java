package app.Model.Mapper;

import app.Model.Comment;
import app.Model.Dto.CommentDto;
import app.Model.Dto.ThumbnailDto;

import java.util.List;
import java.util.stream.Collectors;

public class CommentMapper {

    public static CommentDto mapToDto(Comment comment) {
        List<ThumbnailDto> thumbnails = comment.getThumbnails().stream()
                .map(ThumbnailMapper::mapToDto).collect(Collectors.toList());
        return CommentDto.builder()
                .id(comment.getId())
                .productId(comment.getProduct().getId())

                .username(comment.getUser().getUsername())
                .thumbnails(thumbnails)
                .rating(comment.getRating())
                .message(comment.getMessage())
                .createdAt(comment.getCreatedAt())
                .build();
    }

}
