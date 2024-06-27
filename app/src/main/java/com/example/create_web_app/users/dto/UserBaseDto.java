package com.example.create_web_app.users.dto;

import com.example.create_web_app.common.dto.BaseDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
abstract class UserBaseDto extends BaseDto {
    private String username;
}
