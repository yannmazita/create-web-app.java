package com.example.create_web_app.auth.service;

import com.example.create_web_app.auth.dto.AuthDto;
import com.example.create_web_app.auth.model.Token;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public Token loginForAccessToken(AuthDto authDto) {
        Token token = new Token();
        return token;
    }

    public Token registerForAccessToken(AuthDto authDto) {
        Token token = new Token();
        return token;
    }
}
