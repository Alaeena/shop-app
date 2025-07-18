package app.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = {MERGE, DETACH})
    private Set<Category> subCategories = new HashSet<>();

    @OneToMany(mappedBy = "category", cascade = {MERGE})
    private Set<Product> products = new HashSet<>();

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = {PERSIST, MERGE})
    private Set<CategoryAttribute> attributes = new HashSet<>();

    public Category(String name) {
        this.name = name;
    }

    public void addSubCategory(List<Category> list) {
        list.forEach(this::addSubCategory);
    }

    public void addSubCategory(Category subCategory) {
        this.subCategories.add(subCategory);
        subCategory.setParent(this);
    }

    public void addAttribute(CategoryAttribute attribute) {
        attributes.add(attribute);
        attribute.setCategory(this);
    }

    public void addProduct(Product product) {
        products.add(product);
        product.setCategory(this);
    }

    public void addProduct(List<Product> list) {
        list.forEach(this::addProduct);
    }
}
