package com.example.create_web_app.auth.model;

import lombok.Data;

@Data
public class Token {
    private String accessToken;
    private String tokenType;
}
