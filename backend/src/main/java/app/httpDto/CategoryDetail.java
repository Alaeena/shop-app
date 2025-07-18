package app.httpDto;

import app.model.dto.CategoryDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDetail {
    private Long id;
    private String name;
    private List<CategoryDto> subCategories;
}
