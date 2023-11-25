package app.Model.Criteria;


import app.Model.Category;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class ProductCriteria {
    private String name;
    @NotNull
    private List<Long> categories;

    public ProductCriteria(Set<Category> categories) {
        this.categories = categories.stream()
                .map(Category::getId)
                .collect(Collectors.toList());
    }

    public ProductCriteria(Category category) {
        this.categories = List.of(category.getId());
    }
}
