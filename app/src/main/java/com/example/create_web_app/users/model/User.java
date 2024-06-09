package com.example.create_web_app.users.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.example.create_web_app.auth.model.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends UserBase {
    @Id
    private UUID id;

    private String hashedPassword;

    private Set<Role> roles = new HashSet<>();
}
