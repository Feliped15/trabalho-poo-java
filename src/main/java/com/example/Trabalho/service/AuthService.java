package com.example.Trabalho.service;

import com.example.Trabalho.dto.TokenDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class AuthService {
    private static final String CLIENT_ID = "java";
    private static final String CLIENT_SECRET = "poo@2024";
    private static final long EXPIRATION_TIME = 3600000; // 1 hora

    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public TokenDTO generateToken(String clientId, String clientSecret) {
        if (!CLIENT_ID.equals(clientId) || !CLIENT_SECRET.equals(clientSecret)) {
            throw new RuntimeException("Credenciais inválidas");
        }

        String token = Jwts.builder()
                .setSubject(clientId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        return new TokenDTO(token, EXPIRATION_TIME);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}