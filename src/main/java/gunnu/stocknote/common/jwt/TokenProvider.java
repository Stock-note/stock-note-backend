package gunnu.stocknote.common.jwt;

import gunnu.stocknote.user.entity.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TokenProvider {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String USER_ID = "userId";
    private static final String AUTHORIZATION_KEY = "auth";
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.token-validity-in-milliseconds}")
    private Long accessTokenValidityInMs;

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String createAccessToken(
        final Long userId,
        final String username,
        final UserRole userRole
    ) {
        Date date = new Date();

        return BEARER_PREFIX +
            Jwts.builder()
                .setSubject(username)
                .claim(USER_ID, userId)
                .claim(AUTHORIZATION_KEY, userRole)
                .setIssuedAt(date)
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenValidityInMs))
                .signWith(key, signatureAlgorithm)
                .compact();
    }

    public boolean validateToken(final String token) {
        String parseToken = parseToken(token);
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(parseToken);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty", e);
        }
        return false;
    }


    private Claims parseClaims(final String accessToken) {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(parseToken(accessToken))
            .getBody();
    }

    private String parseToken(final String token) {
        return token.replace(BEARER_PREFIX, "");
    }
}
