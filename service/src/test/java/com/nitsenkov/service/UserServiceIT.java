package com.nitsenkov.service;

import com.nitsenkov.dto.UserCreateEditDto;
import com.nitsenkov.dto.UserReadDto;
import com.nitsenkov.entity.enums.UserRole;
import com.nitsenkov.integration.BaseIntegrationTest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
public class UserServiceIT extends BaseIntegrationTest {

    private final UserService userService;
    private final UUID USER_1 = UUID.fromString("a21e5f62-1352-412c-ad56-697958c6b6bb");

    @Test
    void findAll() {
        List<UserReadDto> result = userService.findAll();
        assertThat(result).hasSize(5);
    }

    @Test
    void findById() {
        Optional<UserReadDto> maybeUser = userService.findById(USER_1);
        assertTrue(maybeUser.isPresent());
        maybeUser.ifPresent(user -> assertEquals("alex", user.getName()));
    }

    @Test
    void create() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "alex",
                "smith",
                "testemail@gmail.com",
                "qwerty",
                UserRole.CLIENT
        );
        UserReadDto actualResult = userService.create(userDto);

        assertEquals(userDto.getName(), actualResult.getName());
        assertEquals(userDto.getSurname(), actualResult.getSurname());
        assertEquals(userDto.getEmail(), actualResult.getEmail());
        assertEquals(userDto.getPassword(), actualResult.getPassword());
        assertSame(userDto.getRole(), actualResult.getRole());
    }

    @Test
    void update() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "alex",
                "smith",
                "testemail@gmail.com",
                "qwerty",
                UserRole.CLIENT
        );

        Optional<UserReadDto> actualResult = userService.update(USER_1, userDto);

        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(user -> {
            assertEquals(userDto.getName(), user.getName());
            assertEquals(userDto.getSurname(), user.getSurname());
            assertEquals(userDto.getEmail(), user.getEmail());
            assertEquals(userDto.getPassword(), user.getPassword());
            assertSame(userDto.getRole(), user.getRole());
        });
    }

    @Test
    void delete() {
        assertFalse(userService.delete(UUID.randomUUID()));
        assertTrue(userService.delete(USER_1));
    }
}