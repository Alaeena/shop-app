package app.model.postgres;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tokens")
public class Token {
    @Id
    @GeneratedValue
    private Long id;

    private String userId;

    private String tokenType;

    private String signature;

    public Token(String userId, String tokenType, String signature) {
        this.userId = userId;
        this.tokenType = tokenType;
        this.signature = signature;
    }
}
