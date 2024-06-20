package com.example.create_web_app.users.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "user")
public class User extends UserBase {
    @Id
    @GeneratedValue()
    private UUID id;
    private String username;
    private String hashedPassword;
    private String roles;
}
