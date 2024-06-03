package com.example.create_web_app.auth.model;

import lombok.Data;

@Data
public class TokenData {
    private String username;
    private String scopes;
}
