package com.example.create_web_app.auth.service;

import java.security.Key;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.example.create_web_app.auth.dto.AuthDto;
import com.example.create_web_app.auth.model.Token;
import com.example.create_web_app.auth.model.TokenData;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.Password;

@Component
public class AuthService {

    Password key = Keys.password(System.getenv("SECRET_KEY").toCharArray());

    private String createToken(Map<String, Object> claims, String username) {

        long expire = Long.parseLong(System.getenv("ACCESS_TOKEN_EXPIRE_MINUTES")) * 1000 * 60;
        Date access_token_expires = new Date(System.currentTimeMillis() + expire);

        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(access_token_expires)
                .signWith(this.key)
                .compact();
    }

    private Jws<Claims> extractAllClaims(String token) {
        try {
            return Jwts
                    .parser()
                    .verifyWith(this.key)
                    .build()
                    .parseSignedClaims(token);
        } catch (JwtException exception) {
            throw new JwtException("Invalid Token");
        }
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String GenerateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }
}
