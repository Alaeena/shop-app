package app.utils.jwt;

import app.model.enums.TokenType;
import app.model.postgres.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;

import static app.model.enums.TokenType.ACCESS_TOKEN;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class JwtRedisUtils extends JwtUtils {
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public String buildToken(UserEntity userEntity, TokenType tokenType) {
        long expiration = tokenType == ACCESS_TOKEN ? jwtExpiration : refreshExpiration;
        String prefix = tokenType == ACCESS_TOKEN ? ACCESS_TOKEN_PREFIX : REFRESH_TOKEN_PREFIX;
        String token = Jwts.builder()
                .setClaims(new HashMap<>())
                .setId(userEntity.getId().toString())
                .setSubject(userEntity.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + Duration.ofSeconds(expiration).toMillis()))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        String signature = token.split("\\.")[2];
        redisTemplate.opsForValue().set(prefix + userEntity.getId(), signature, Duration.ofSeconds(expiration));
        return token;
    }

    @Override
    public void revokeToken(String token) {
        Claims claims = extractAllClaim(token);
        String tokenId = claims.getId();
        redisTemplate.delete(REFRESH_TOKEN_PREFIX + tokenId);
        redisTemplate.delete(ACCESS_TOKEN_PREFIX + tokenId);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails, TokenType tokenType) {
        Claims claims = extractAllClaim(token);
        String prefix = tokenType == ACCESS_TOKEN ? ACCESS_TOKEN_PREFIX : REFRESH_TOKEN_PREFIX;
        String signature = token.split("\\.")[2];

        String email = claims.getSubject();
        String uuid = claims.getId();
        String cacheSignature = redisTemplate.opsForValue().get(prefix + uuid);

        return userDetails.getUsername().equals(email)
                && signature.equals(cacheSignature);
    }
}
