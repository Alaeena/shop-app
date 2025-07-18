package app.model.dto;


import app.model.enums.AddressType;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDto {
    private Long id;
    private AddressType addressType;

    @NotEmpty(message = "address cannot be empty")
    private String address;

    @NotEmpty(message = "receiver cannot be empty")
    private String receiver;

    @NotEmpty(message = "phone cannot be empty")
    private String phone;
}
