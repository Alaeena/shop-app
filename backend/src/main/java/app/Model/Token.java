package app.Model;

import app.Model.Enum.TokenType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Token {
    @Id
    @GeneratedValue
    private Long id;

    private boolean revoked;
    private String token;
    private TokenType type;

    @OneToOne
    private UserEntity user;

}
