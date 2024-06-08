package com.example.create_web_app.users.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class UserBase {
    @Column(unique = true, nullable = false)
    private String username;
}
