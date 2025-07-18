package app.model.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopDto {
    private Long id;
    private LocalDate createdOn;
    private String name;
    private String url;
}
