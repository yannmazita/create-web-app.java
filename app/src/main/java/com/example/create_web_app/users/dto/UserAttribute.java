package com.example.create_web_app.users.dto;

import lombok.Getter;

@Getter
public enum UserAttribute {
    ID("id"),
    USERNAME("username");

    private String attribute;

    UserAttribute(String attribute) {
        this.attribute = attribute;
    }
}
