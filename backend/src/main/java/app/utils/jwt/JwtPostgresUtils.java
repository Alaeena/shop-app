package app.utils.jwt;

import app.model.enums.TokenType;
import app.model.postgres.Token;
import app.model.postgres.UserEntity;
import app.repository.postgres.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import static app.model.enums.TokenType.ACCESS_TOKEN;

@Component
@Profile("production")
@RequiredArgsConstructor
public class JwtPostgresUtils extends JwtUtils {
    private final TokenRepository tokenRepository;

    @Override
    public String buildToken(UserEntity userEntity, TokenType tokenType) {
        long expiration = tokenType == ACCESS_TOKEN ? jwtExpiration : refreshExpiration;
        Date expiryDate = new Date(System.currentTimeMillis() + Duration.ofSeconds(expiration).toMillis());

        String token = Jwts.builder()
                .setClaims(new HashMap<>())
                .setId(userEntity.getId().toString())
                .setSubject(userEntity.getUsername())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        String signature = token.split("\\.")[2];

        // Replace existing token
        tokenRepository.deleteByUserIdAndTokenType(userEntity.getId().toString(), tokenType.name());
        tokenRepository.save(new Token(userEntity.getId().toString(), tokenType.name(), signature));
        return token;
    }

    @Override
    public void revokeToken(String token) {
        Claims claims = extractAllClaim(token);
        String userId = claims.getId();

        tokenRepository.deleteByUserId(userId);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails, TokenType tokenType) {
        Claims claims = extractAllClaim(token);
        String userId = claims.getId();
        String email = claims.getSubject();
        String signature = token.split("\\.")[2];

        Optional<Token> storedTokenOpt = tokenRepository.findByUserIdAndTokenType(userId, tokenType.name());

        return storedTokenOpt
                .map(storedToken -> storedToken.getSignature().equals(signature)
                        && userDetails.getUsername().equals(email))
                .orElse(false);
    }
}