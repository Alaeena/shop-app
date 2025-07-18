package app.utils;

import app.model.UserEntity;
import app.model.enums.TokenType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;

import static app.model.enums.TokenType.ACCESS_TOKEN;

@Component
@RequiredArgsConstructor
public class JwtUtils {
    private final RedisTemplate<String, String> redisTemplate;
    private final String REFRESH_TOKEN_PREFIX = "refresh_token_";
    private final String ACCESS_TOKEN_PREFIX = "access_token_";

    @Value("${spring.security.jwt.secret-key}")
    private String secretKey;
    @Value("${spring.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${spring.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;


    private Claims extractAllClaim(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public String extractEmail(String token) {
        Claims claims = extractAllClaim(token);
        return claims.getSubject();
    }

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

    public void revokeToken(String token) {
        Claims claims = extractAllClaim(token);
        String tokenId = claims.getId();
        redisTemplate.delete(REFRESH_TOKEN_PREFIX + tokenId);
        redisTemplate.delete(ACCESS_TOKEN_PREFIX + tokenId);
    }

    public boolean isTokenValid(String token, UserDetails userDetails, TokenType tokenType) {
        Claims claims = extractAllClaim(token);
        String prefix = tokenType == ACCESS_TOKEN ? ACCESS_TOKEN_PREFIX : REFRESH_TOKEN_PREFIX;
        String signature = token.split("\\.")[2];

        String email = claims.getSubject();
        String uuid = claims.getId();
        String cacheSignature = redisTemplate.opsForValue().get(prefix + uuid);

        return userDetails.getUsername().equals(email)
                && cacheSignature.equals(signature);
    }


}
