package com.example.create_web_app.users.dto;

import com.example.create_web_app.users.model.UserBase;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserCreate extends UserBase {
    @NotNull
    private String password;
}
