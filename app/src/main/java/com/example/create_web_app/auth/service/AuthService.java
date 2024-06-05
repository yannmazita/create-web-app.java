package com.example.create_web_app.auth.service;

import com.example.create_web_app.auth.dto.AuthDto;
import com.example.create_web_app.auth.model.Token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

public class AuthService {

    private String createAccessToken(Map<String, Object> data, long expiresDelta) {

        long nowTime = System.currentTimeMillis();
        Date now = new Date(nowTime);

        Password key = Keys.password(System.getenv("SECRET_KEY").toCharArray());

        // Depending on key length, either HS256, HS384 or HS512 will be used
        String jws = Jwts.builder()
                .subject(data.get("sub").toString())
                .scopes(data.get("scopes").toString())
                .signWith(key)
                .compact();
        return jws;
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
