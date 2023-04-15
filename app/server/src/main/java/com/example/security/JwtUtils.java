package com.example.security;

import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private final int jwtExpirationDays = 365;

    private final String jwtSecret = "MTI2ZTc1NzAtMjJlMy00MmVlLTkwYmQtOTVjNGM4ZTRhN2YzMTI2ZTc1NzAtMjJlMy00MmVlLTkwYmQtOTVjNGM4ZTRhN2Yz";

    public int getJwtExpirationSeconds() {
        return jwtExpirationDays * 24 * 60 * 60;
    }

    public Long getJwtExpirationMs() {
        return jwtExpirationDays * 1000 * 60 * 60 * 24L;
    }

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + getJwtExpirationMs()))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            System.out.println("Invalid JWT signature: " + e.getMessage());
        }

        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }
}
