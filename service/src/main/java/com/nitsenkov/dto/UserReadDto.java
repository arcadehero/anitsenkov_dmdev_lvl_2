package com.nitsenkov.dto;

import com.nitsenkov.entity.enums.UserRole;
import lombok.Value;

import java.util.UUID;

@Value
public class UserReadDto {

    UUID id;
    String name;
    String surname;
    String email;
    String password;
    UserRole role;
}
