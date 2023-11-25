package app.Model.Dto;


import app.Model.Enum.AddressType;
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

    @NotEmpty(message = "name cannot be empty")
    private String name;

    @NotEmpty(message = "phone cannot be empty")
    private String phone;
}
