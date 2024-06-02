package com.example.create_web_app.auth.service;

import com.example.create_web_app.auth.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private TokenStore tokenStore;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Token login(String username, String password) {
        // Authenticate user and generate token
        // Dummy implementation
        if ("user".equals(username) && "password".equals(password)) {
            Token token = new Token();
            token.setAccessToken("dummy_access_token");
            token.setTokenType("bearer");
            return token;
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    public Token register(String username, String password) {
        // Register user and generate token
        // Dummy implementation
        Token token = new Token();
        token.setAccessToken("dummy_access_token");
        token.setTokenType("bearer");
        return token;
    }
}
