package app.HttpDto;

import app.Model.Enum.CheckoutType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderCreationRequest {
    @NotNull
    public Long addressId;
    @NotNull
    public List<Long> items;
    @NotNull
    public CheckoutType checkoutType;
}
