package com.example.create_web_app.users.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserCreate extends UserBaseDto {
    @NotNull
    private String password;
}
