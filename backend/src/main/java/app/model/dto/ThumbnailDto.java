package app.model.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThumbnailDto {
    private String url;
    private String type;
}
