package com.example.create_web_app.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.create_web_app.auth.dto.AuthDto;
import com.example.create_web_app.auth.dto.Token;
import com.example.create_web_app.auth.service.AuthService;
import com.example.create_web_app.auth.config.WebSecurityConfig;

/**
 * The AuthController class is responsible for handling the authentication
 * requests.
 */
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * The login method is used to authenticate the user.
     * 
     * @param authDto the authentication details
     * @return Token
     */
    @PostMapping("/login")
    public Token login(@RequestBody AuthDto authDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword()));
        if (authentication.isAuthenticated()) {
            Token token = new Token();
            token.setAccessToken(authService.generateToken(authDto.getUsername()));
            token.setTokenType("Bearer");
            return token;
        }
    }

    /**
     * The register method is used to register a new user.
     * 
     * @param authDto the authentication details
     * @return Token
     */
    @PostMapping("/register")
    public Token register(@RequestBody AuthDto authDto) {
        Token token = new Token();
        return token;
    }
}
