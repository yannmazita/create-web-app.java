package com.example.create_web_app.auth.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthDto {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
