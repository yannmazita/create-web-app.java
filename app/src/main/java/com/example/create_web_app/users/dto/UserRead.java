package com.example.create_web_app.users.dto;

import java.util.UUID;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserRead extends UserBaseDto {
    private UUID id;
    private String roles;
}
