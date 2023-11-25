package app.Model.Dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private Long id;
    @NotNull
    private Long productId;
    @NotNull
    private String message;
    private LocalDate createdAt;
    @NotNull
    @Min(value = 1)
    @Max(value = 5)
    private Integer rating;
    private List<ThumbnailDto> thumbnails;
    private String username;

}
