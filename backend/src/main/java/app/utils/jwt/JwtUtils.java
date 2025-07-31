package app.utils.jwt;

import app.model.enums.TokenType;
import app.model.postgres.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

public abstract class JwtUtils {
    public static String REFRESH_TOKEN_PREFIX = "refresh_token_";
    public static String ACCESS_TOKEN_PREFIX = "access_token_";
    @Value("${spring.security.jwt.secret-key}")
    protected String secretKey;
    @Value("${spring.security.jwt.expiration}")
    protected long jwtExpiration;
    @Value("${spring.security.jwt.refresh-token.expiration}")
    protected long refreshExpiration;

    public String extractEmail(String token) {
        Claims claims = extractAllClaim(token);
        return claims.getSubject();
    }

    protected Claims extractAllClaim(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public abstract String buildToken(UserEntity userEntity, TokenType tokenType);

    public abstract void revokeToken(String token);

    public abstract boolean isTokenValid(String token, UserDetails userDetails, TokenType tokenType);
}
