package com.nitsenkov.integration.dao;

import com.nitsenkov.entity.User;
import com.nitsenkov.entity.enums.UserRole;
import com.nitsenkov.integration.BaseIntegrationTest;
import com.nitsenkov.repository.UserRepository;
import com.nitsenkov.util.TestObjectsBuilder;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class UserRepositoryIT extends BaseIntegrationTest {

    private User user;
    private final UserRepository userRepository;

    @BeforeEach
    void setUp() {
        user = TestObjectsBuilder.getUser("testEmail");
    }

    @Test
    void saveUser() {
        User savedUser = userRepository.save(user);
        session.clear();
        Optional<User> actualUser = userRepository.findById(savedUser.getId());

        actualUser.ifPresent(u -> assertThat(u).isEqualTo(savedUser));
    }

    @Test
    void updateUser() {
        User savedUser = userRepository.save(user);
        user.setRole(UserRole.ADMINISTRATOR);

        userRepository.save(user);
        session.clear();
        Optional<User> actualUser = userRepository.findById(savedUser.getId());

        actualUser.ifPresent(u -> assertThat(u.getRole()).isEqualTo(UserRole.ADMINISTRATOR));
    }

    @Test
    void deleteUser() {
        User savedUser = userRepository.save(user);

        userRepository.delete(savedUser);
        Optional<User> actualUser = userRepository.findById(savedUser.getId());

        assertThat(actualUser.isPresent()).isFalse();
    }

    @Test
    void findUserById() {
        User user1 = TestObjectsBuilder.getUser("testEmail1");
        User user2 = TestObjectsBuilder.getUser("testEmail2");
        User user3 = TestObjectsBuilder.getUser("testEmail3");
        userRepository.save(user);
        userRepository.save(user1);
        userRepository.save(user2);
        User savedUser3 = userRepository.save(user3);
        session.clear();

        Optional<User> actualUser = userRepository.findById(savedUser3.getId());

        actualUser.ifPresent(u -> assertThat(u.getEmail()).isEqualTo(user3.getEmail()));
    }
}
