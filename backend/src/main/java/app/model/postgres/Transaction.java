package app.model.postgres;

import app.model.enums.VnpResponseCode;
import app.model.enums.VnpTransactionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {
    LocalDateTime paymentDate;
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.MERGE)
    private List<Order> orders = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    private long amount;

    private String orderInfo;
    private String bankTransactionNo;
    private String transactionNo;

    @Column(length = 20)
    private String bankCode;
    @Column(length = 20)
    private String cardType;
    @Column(length = 100)
    private String TxnRef;

    @Enumerated(EnumType.STRING)
    private VnpTransactionStatus status;
    @Enumerated(EnumType.STRING)
    private VnpResponseCode responseCode;
}
