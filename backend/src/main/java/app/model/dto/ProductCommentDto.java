package app.model.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCommentDto {
    List<CommentDto> comments;
    List<SimpleProductDto> products;
}
