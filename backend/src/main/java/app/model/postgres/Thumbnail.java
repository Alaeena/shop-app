package app.model.postgres;

import app.model.enums.ThumbnailType;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Thumbnail {
    @Id
    @GeneratedValue
    private Long id;
    private String url;

    @Enumerated(EnumType.STRING)
    private ThumbnailType type;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public Thumbnail(String url) {
        this.url = url;
        this.type = ThumbnailType.IMAGE;
    }

    public Thumbnail(String url, ThumbnailType type) {
        this.url = url;
        this.type = type;
    }
}
