package com.example.create_web_app.auth.controller;

import com.example.create_web_app.auth.model.Token;
import com.example.create_web_app.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Token login(@RequestParam String username, @RequestParam String password) {
        return authService.login(username, password);
    }

    @PostMapping("/register")
    public Token register(@RequestParam String username, @RequestParam String password) {
        return authService.register(username, password);
    }
}
