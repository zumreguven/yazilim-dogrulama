package com.example.careermanagement.security;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${jwt.gizli-anahtar}")
    private String gizliAnahtar;

    @Value("${jwt.jwt-sure}")
    private int jwtSure;

    public String jwtOlustur(Authentication authentication) {
        KullaniciDetaylariImpl kullaniciDetaylari = (KullaniciDetaylariImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((kullaniciDetaylari.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtSure))
                .signWith(SignatureAlgorithm.HS512, gizliAnahtar)
                .compact();
    }

    public String kullaniciAdiniAl(String token) {
        return Jwts.parser().setSigningKey(gizliAnahtar).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean dogrula(String authToken) {
        try {
            Jwts.parser().setSigningKey(gizliAnahtar).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Geçersiz JWT imzası: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Geçersiz JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token süresi doldu: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token desteklenmiyor: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims boş: {}", e.getMessage());
        }

        return false;
    }
}