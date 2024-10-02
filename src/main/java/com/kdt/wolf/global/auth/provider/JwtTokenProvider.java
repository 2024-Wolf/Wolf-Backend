package com.kdt.wolf.global.auth.provider;

import com.kdt.wolf.domain.user.dto.LoginDto.TokenResponse;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.global.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;
import javax.crypto.SecretKey;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@NoArgsConstructor
public class JwtTokenProvider {
    @Value("${security.access-token.expiry-in-millis}")
    private static long ACCESS_TOKEN_EXPIRE_TIME;
    private static final String BEARER_TYPE = "bearer";

    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;

    @Value("${security.access-token.jwt-secret-key}")
    private String signatureSecretKey;
    private SecretKey key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(signatureSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public TokenResponse generateJwtTokenResponse(UserEntity user) {
        long now = getNowDateTime();
        String accessToken = generateAccessTokenValue(user, now);
        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        String refreshToken = createRefreshToken(now);
        return new TokenResponse(BEARER_TYPE, accessToken, refreshToken, accessTokenExpiresIn.getTime());
    }

    public String generateAccessTokenValue(UserEntity user, long now) {
        return Jwts.builder()
                .subject(String.valueOf(user.getUserId()))
                .issuedAt(new Date())
                .expiration(new Date(now + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(key)
                .compact();
    }

    public String createRefreshToken(long now) {
        return Jwts.builder()
                .expiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(this.key)
                .compact();
    }

    private static long getNowDateTime() {
        return (new Date(System.currentTimeMillis())).getTime();
    }

    public void validateToken(String jwtToken) {
        try {
            Date expiration = parseClaimsJws(jwtToken).getPayload().getExpiration();

            if (expiration.before(new Date())) {
                throw new UnauthorizedException();
            }
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
            throw new UnauthorizedException();
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
            throw new UnauthorizedException();
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
            throw new UnauthorizedException();
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
            throw new UnauthorizedException();
        }
    }

    private Jws<Claims> parseClaimsJws(String jwtToken) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(jwtToken);
    }

    public Optional<Object> getClaimValue(String jwtToken, String claimName) {
        return Optional.ofNullable(parseClaimsJws(jwtToken).getPayload().get(claimName));
    }

    public Date getExpirationDateFromToken(String token) {
        Jws<Claims> claims = parseClaimsJws(token);
        return claims.getPayload().getExpiration();  // 만료 시간 추출
    }

    public String getSubject(String jwtToken) {
        Jws<Claims> jws = parseClaimsJws(jwtToken);

        return jws.getPayload().getSubject();
    }
}
