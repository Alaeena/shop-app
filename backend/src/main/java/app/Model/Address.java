package app.Model;

import app.Model.Dto.AddressDto;
import app.Model.Enum.AddressType;
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
    private String name;
    private String phone;

    @ManyToOne(cascade = MERGE)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public Address(AddressDto addressDto) {
        this.addressType = addressDto.getAddressType();
        this.address = addressDto.getAddress();
        this.name = addressDto.getName();
        this.phone = addressDto.getPhone();
    }
}
