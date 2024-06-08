package com.example.create_web_app.users.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserPasswordUpdate {

    @NotNull
    private String oldPassword;

    @NotNull
    private String newPassword;

    @NotNull
    private String confirmPassword;

    public void validatePasswords() {
        if (!newPassword.equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        if (oldPassword.equals(newPassword)) {
            throw new IllegalArgumentException("New password is the same as the old password");
        }
    }
}
