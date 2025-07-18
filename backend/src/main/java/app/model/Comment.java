package app.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 255)
    private String message;
    private LocalDate createdAt;
    private Integer rating;

    @OneToMany(mappedBy = "comment", cascade = ALL)
    private List<Thumbnail> thumbnails = new ArrayList<>();

    @ManyToOne(cascade = MERGE, fetch = LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(cascade = MERGE, fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public Comment(String message, Integer rating) {
        this.message = message;
        this.createdAt = LocalDate.now();
        this.rating = rating;
    }

    public Comment(String message) {
        this.message = message;
        this.createdAt = LocalDate.now();
        this.rating = new Random().nextInt(5) + 1;
    }

}
