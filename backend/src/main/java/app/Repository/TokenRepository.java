package app.Repository;

import app.Model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    boolean existsByToken(String token);

    Optional<Token> findByToken(String token);
}
