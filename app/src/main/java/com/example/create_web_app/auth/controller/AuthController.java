package com.example.create_web_app.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.example.create_web_app.auth.dto.AuthDto;
import com.example.create_web_app.auth.dto.CustomUserDetails;
import com.example.create_web_app.auth.dto.Token;
import com.example.create_web_app.auth.service.AuthService;

/**
 * The AuthController class is responsible for handling the authentication
 * requests.
 */
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public Token login(@RequestBody AuthDto authDto) throws IllegalAccessException {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        authDto.getUsername(),
                        authDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Token token = new Token();
        token.setAccessToken(authService.generateAccessToken(authentication));
        token.setTokenType("Bearer");
        return token;
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
