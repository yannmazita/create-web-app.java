package com.example.create_web_app.users.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
class User extends UserBase {
    @Id
    private UUID id;

    private String hashedPassword;

    @Column(columnDefinition = "varchar(255) default ''")
    private String roles;
}
