package app.model.dto;

import app.model.enums.Role;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private Boolean activated;

    @NotNull(message = "firstName must not be empty")
    private String firstName;

    @NotNull(message = "lastName must not be empty")
    private String lastName;

    @NotNull(message = "email must not be empty")
    private String email;

    @NotNull(message = "two factor authentication must not be empty")
    private Boolean tfaEnabled;

    private Long shop;
    private Role role;
}
