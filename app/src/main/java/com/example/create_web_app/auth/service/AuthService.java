package com.example.create_web_app.auth.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.example.create_web_app.auth.dto.AuthDto;
import com.example.create_web_app.auth.model.Token;
import com.example.create_web_app.auth.model.TokenData;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.Password;

public class AuthService {

    private String createAccessToken(TokenData data, long expiresDelta) {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expire = now.plus(Duration.ofMillis(expiresDelta));

        Password key = Keys.password(System.getenv("SECRET_KEY").toCharArray());

        // Depending on key length, either HS256, HS384 or HS512 will be used
        String jws = Jwts.builder()
                .subject(data.getUsername())
                .expiration(Date.from(expire.atZone(ZoneId.systemDefault()).toInstant()))
                .claim("scopes", data.getScopes())
                .signWith(key)
                .compact();
        return jws;
    }

    public Token loginForAccessToken(AuthDto authDto) {
        Token token = new Token();
        long accessTokenExpires = Long.parseLong(System.getenv("ACCESS_TOKEN_EXPIRE_MINUTES"));
        String access_token = createAccessToken(null, accessTokenExpires);
        return token;
    }

    public Token registerForAccessToken(AuthDto authDto) {
        Token token = new Token();
        return token;
    }
}