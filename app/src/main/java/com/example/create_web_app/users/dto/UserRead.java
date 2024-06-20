package com.example.create_web_app.users.dto;

import java.util.UUID;

import com.example.create_web_app.users.model.UserBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserRead extends UserBase {
    private UUID id;
    private String roles;
}
