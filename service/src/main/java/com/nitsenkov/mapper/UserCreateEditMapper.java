package com.nitsenkov.mapper;

import com.nitsenkov.dto.UserCreateEditDto;
import com.nitsenkov.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {

    @Override
    public User map(UserCreateEditDto fromObject, User toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public User map(UserCreateEditDto object) {
        User user = new User();
        copy(object, user);
        return user;
    }

    private static void copy(UserCreateEditDto object, User user) {
        user.setName(object.getName());
        user.setSurname(object.getSurname());
        user.setPassword(object.getPassword());
        user.setEmail(object.getEmail());
        user.setRole(object.getRole());
    }
}
