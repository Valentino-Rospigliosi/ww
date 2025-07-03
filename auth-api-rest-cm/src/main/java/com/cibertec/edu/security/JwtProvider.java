package com.cibertec.edu.security;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cibertec.edu.models.AuthUsuario;

import jakarta.annotation.PostConstruct;

@Component
public class JwtProvider {    

    private Key secret;
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);

    @PostConstruct
    protected void init(){
        byte[] apiKeySecretBytes = new byte[64]; // 512 bits
        new SecureRandom().nextBytes(apiKeySecretBytes);
        secret = Keys.hmacShaKeyFor(apiKeySecretBytes);        
    }

    public String createToken(AuthUsuario authUser){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",authUser.getId());        
        Date now = new Date();
        Date exp = new Date(now.getTime() + 3600000); // 1 hora de validez
        return Jwts.builder()
                .claims(claims)
                .subject(authUser.getUsername())
                .issuedAt(now)
                .expiration(exp)
                .signWith(Keys.hmacShaKeyFor(secret.getEncoded()))
                .compact();        
    }

    public boolean validate(String token){
        try{            
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getEncoded())).build().parseSignedClaims(token);
        }catch (ExpiredJwtException ex) {
        	LOGGER.warn("Token expirado: {}", ex.getMessage());
            return false;
        } catch (Exception ex) {
        	LOGGER.error("Error validando token: {}", ex.getMessage());
            return false;
        }   
        return true;
    }

    public String getUserNameFromToken(String token){
        try{            
            return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getEncoded()))
                    .build()
                    .parseSignedClaims(token).getPayload().getSubject();
        }catch (Exception exception){
            return "Bad token";
        }
    }    
}
