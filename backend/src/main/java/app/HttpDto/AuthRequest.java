package app.HttpDto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AuthRequest {
    @Pattern(message = "Email is not valid", regexp = "(^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$)|(^admin$)")
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    @NotNull(message = "Password must not be empty")
    private String password;
}
