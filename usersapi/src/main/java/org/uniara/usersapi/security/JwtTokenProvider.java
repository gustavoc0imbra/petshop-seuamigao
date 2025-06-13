package org.uniara.usersapi.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String secretKey;
    private final long validityInMilliseconds = 3600000; // 1 hour to expire
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    public String generateToken(String username) {
        Date now = new Date();
        Date validaty = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(validaty)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        }catch (ExpiredJwtException e){
            LOGGER.info("Token expirado: {}", token, e);
            return false;
        }catch (SignatureException e){
            LOGGER.warn("Token com assinatura inválida: {}", token, e);
            return false;
        }
    }
}
