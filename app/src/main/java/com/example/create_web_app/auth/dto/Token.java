package com.example.create_web_app.auth.dto;

import lombok.Data;

@Data
public class Token {
    private String accessToken;
    private String tokenType;
}
