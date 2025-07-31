package app.repository.postgres;

import app.model.postgres.Token;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Profile("production")
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByUserIdAndTokenType(String userId, String tokenType);

    void deleteByUserId(String userId);

    void deleteByUserIdAndTokenType(String userId, String tokenType);
}
