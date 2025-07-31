package app.model.mapper;

import app.model.dto.CommentDto;
import app.model.dto.ThumbnailDto;
import app.model.postgres.Comment;

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
