package app.model.postgres;

import app.model.enums.AddressType;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.CascadeType.MERGE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Address {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;
    private String address;
    private String receiver;
    private String phone;

    @ManyToOne(cascade = MERGE)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
