package app.model.postgres;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.MERGE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "attribute_value")
public class AttributeValue {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String value;

    @ManyToMany(mappedBy = "attributes")
    private Set<CategoryAttribute> categoryAttributes = new HashSet<>();

    @ManyToMany(mappedBy = "attributes", cascade = MERGE)
    private Set<Product> products = new HashSet<>();

    public AttributeValue(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public void addAttribute(CategoryAttribute attribute) {
        categoryAttributes.add(attribute);
    }

    public void addProduct(Product product) {
        products.add(product);
    }
}
