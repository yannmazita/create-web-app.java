package com.example.create_web_app.auth.dto;

import lombok.Data;

/**
 * The Token class is used to store the token details.
 */
@Data
public class Token {
    private String accessToken;
    private String tokenType;
}
