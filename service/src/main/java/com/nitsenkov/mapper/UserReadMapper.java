package com.nitsenkov.mapper;

import com.nitsenkov.dto.UserReadDto;
import com.nitsenkov.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserReadMapper implements Mapper<User, UserReadDto> {

    @Override
    public UserReadDto map(User object) {
        return new UserReadDto(
                object.getId(),
                object.getName(),
                object.getSurname(),
                object.getEmail(),
                object.getPassword(),
                object.getRole()
        );
    }
}
