package app.model;

import app.model.enums.OrderState;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.CascadeType.MERGE;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Timestamp {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderState state;
    private LocalDateTime time;

    @ManyToOne(cascade = MERGE)
    @JoinColumn(name = "order_id")
    private Order order;

    public Timestamp(OrderState state) {
        this.state = state;
        this.time = LocalDateTime.now();
    }
}
