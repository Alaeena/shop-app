package app.httpDto;

import app.model.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class UserResponse {
    private UserDto userDto;
    private String access_token;
    private String refresh_token;
}
