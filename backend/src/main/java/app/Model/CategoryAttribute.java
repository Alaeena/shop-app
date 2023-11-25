package app.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.PERSIST;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "category_attribute")
public class CategoryAttribute {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER, cascade = PERSIST)
    @JoinTable(
            name = "category_attribute_value",
            joinColumns = @JoinColumn(name = "category_attribute_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_value_id")
    )
    private Set<AttributeValue> attributes = new HashSet<>();

    public CategoryAttribute(String name) {
        this.name = name;
    }

    public void addValue(AttributeValue value) {
        attributes.add(value);

        value.addAttribute(this);
    }
}
