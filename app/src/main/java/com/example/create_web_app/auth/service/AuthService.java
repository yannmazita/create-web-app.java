package com.example.create_web_app.auth.service;

import com.example.create_web_app.auth.dto.AuthDto;
import com.example.create_web_app.auth.model.Token;

import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private String createAccessToken(Map<String, Object> data, long expiresDelta) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowTime = System.currentTimeMillis();
        Date now = new Date(nowTime);

        return "";
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
