package app.Model.Dto;

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
