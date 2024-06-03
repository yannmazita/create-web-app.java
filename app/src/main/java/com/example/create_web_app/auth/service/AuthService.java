package com.example.create_web_app.auth.service;

import com.example.create_web_app.auth.dto.AuthDto;
import com.example.create_web_app.auth.model.Token;

import java.util.Date;
import java.util.Map;

import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private String createAccessToken(Map<String, Object> claims, String username) {
        Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(System.getenv("SECRET_KEY"), SignatureAlgorithm.ES256).compact();
    }

    public Token loginForAccessToken(AuthDto authDto) {
        Token token = new Token();
        return token;
    }

    public Token registerForAccessToken(AuthDto authDto) {
        Token token = new Token();
        return token;
    }
}
