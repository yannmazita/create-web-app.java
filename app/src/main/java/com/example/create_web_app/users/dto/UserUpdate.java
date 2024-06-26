package com.example.create_web_app.users.dto;

import java.util.Optional;

import com.example.create_web_app.common.dto.BaseDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserUpdate extends BaseDto {
    private Optional<String> username;
    private Optional<String> oldPassword;
    private Optional<String> newPassword;
    private Optional<String> confirmPassword;
    private Optional<String> roles;

    public UserUpdate() {
        this.validateUsername();
        this.validatePasswords();
        this.validateRoles();
    }

    private void validateUsername() {
        if (this.username != null) {
            if (this.username.get().length() < 3) {
                throw new IllegalArgumentException("Username must be at least 3 characters");
            }
            if (this.username.get().length() > 50) {
                throw new IllegalArgumentException("Username must be at most 50 characters");
            }
        }
    }

    private void validatePasswords() {
        if (!this.newPassword.equals(this.confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        if (this.oldPassword.equals(this.newPassword)) {
            throw new IllegalArgumentException("New password is the same as the old password");
        }
    }

    private void validateRoles() {
        if (this.roles != null) {
            // implement role validation (spring might already do its thing)
        }
    }
}
