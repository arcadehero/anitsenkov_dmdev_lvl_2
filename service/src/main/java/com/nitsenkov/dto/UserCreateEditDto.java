package com.nitsenkov.dto;

import com.nitsenkov.entity.enums.UserRole;
import lombok.Value;

@Value
public class UserCreateEditDto {

    String name;
    String surname;
    String email;
    String password;
    UserRole role;
}
