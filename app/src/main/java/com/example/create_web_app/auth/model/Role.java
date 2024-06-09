package com.example.create_web_app.auth.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Role {
    @Id
    private UUID id;

    private String name;
    private String description;
}
