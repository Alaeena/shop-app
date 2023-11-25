package app.Model.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimpleProductDto {
    private Long id;
    private String name;
    private String url;
}
