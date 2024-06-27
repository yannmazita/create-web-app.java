package com.example.create_web_app.users.dto;

import java.util.List;

import com.example.create_web_app.common.dto.BaseDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Users extends BaseDto {
    private List<UserRead> users;
    private int total;
}
