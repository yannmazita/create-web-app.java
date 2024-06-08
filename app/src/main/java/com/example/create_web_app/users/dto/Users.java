package com.example.create_web_app.users.dto;

import java.util.List;

import lombok.Data;

@Data
class Users {
    private List<UserRead> users;
    private int total;
}
