package app.model.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentListDto {
    List<CommentDto> comments;
    private Integer page;
    private Integer maxPage;
}
