package com.example.create_web_app.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.create_web_app.auth.dto.AuthDto;
import com.example.create_web_app.auth.service.AuthService;
import com.example.create_web_app.auth.model.Token;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Token login(@RequestBody AuthDto authDto) {
        return authService.loginForAccessToken(authDto);
    }

    @PostMapping("/register")
    public Token register(@RequestBody AuthDto authDto) {
        return authService.registerForAccessToken(authDto);
    }
}
