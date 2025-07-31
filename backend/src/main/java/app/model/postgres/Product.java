package app.model.postgres;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.*;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.FetchType.EAGER;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer price;

    private Float rating;
    private Integer love;
    private Integer sold;
    private Integer quantity;
    private Integer discount;

    @UpdateTimestamp
    private LocalDateTime updatedOn;
    private LocalDateTime createdOn;

    @Column(length = 1000)
    private String description;

    @OneToMany(mappedBy = "product", cascade = ALL, fetch = EAGER)
    private List<Thumbnail> thumbnails = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = ALL)
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne(cascade = MERGE)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne(cascade = MERGE)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(cascade = {MERGE}, fetch = EAGER)
    @JoinTable(
            name = "product_specification",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_value_id")
    )
    private Set<AttributeValue> specification = new HashSet<>();

    @ManyToMany(cascade = {MERGE}, fetch = EAGER)
    @JoinTable(
            name = "product_attribute",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_value_id")
    )
    private Set<AttributeValue> attributes = new HashSet<>();

    public Product(String name, Integer price, String description) {
        this(name, price);
        this.description = description;
    }

    public Product(String name, Integer price) {
        Random random = new Random();
        this.name = name;
        this.price = price;
        this.description = "Gigabyte Gaming G5 i5 (KC-5S11130SB) thỏa mãn các game thủ với vẻ ngoài bắt mắt, hiện đại cùng hiệu năng đột phá đến từ bộ CPU Intel Gen 10 mạnh mẽ, hứa hẹn sẽ là một đồng đội cùng bạn chinh chiến trên mọi mặt trận ảo.";

        Float randomVal = (random.nextFloat() * 3 + 2);
        this.rating = (float) (Math.round(randomVal * 10) / 10);
        this.love = random.nextInt(10000);
        this.quantity = random.nextInt(10000) + 2000;
        this.sold = random.nextInt(2000);
        this.discount = random.nextInt(40);
        this.createdOn = LocalDateTime.now();
    }

    public void addThumbnail(List<String> thumbnail) {
        thumbnail.forEach(item -> {
            Thumbnail thumbnailItem = new Thumbnail(item);
            this.thumbnails.add(thumbnailItem);
            thumbnailItem.setProduct(this);
        });
    }

    public void addSpec(AttributeValue value) {
        specification.add(value);
        value.addProduct(this);
    }

    public void addValue(AttributeValue value) {
        attributes.add(value);
        value.addProduct(this);
    }

}
