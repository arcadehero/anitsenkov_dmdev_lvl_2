package com.nitsenkov.entity;

import com.nitsenkov.BaseIntegrationTest;
import com.nitsenkov.entity.enums.UserRole;
import org.junit.jupiter.api.Test;

import static com.nitsenkov.util.TestObjectsBuilder.getUser;
import static org.assertj.core.api.Assertions.assertThat;

public class UserIT extends BaseIntegrationTest {

    @Test
    void create() {
        User user = getUser("testEmail");

        session.persist(user);
        session.flush();
        session.clear();

        assertThat(session.get(User.class, user.getId())).isNotNull();
    }

    @Test
    void read() {
        User user1 = getUser("testEmail1");
        session.persist(user1);
        session.flush();
        session.clear();

        User actualUser = session.find(User.class, user1.getId());

        assertThat(actualUser.getEmail()).isEqualTo(user1.getEmail());
    }

    @Test
    void update() {
        User user = getUser("testEmail");
        session.persist(user);
        session.flush();
        session.clear();

        user.setRole(UserRole.ADMINISTRATOR);
        session.merge(user);
        session.flush();
        session.clear();
        User actualUpdatedUser = session.find(User.class, user.getId());

        assertThat(actualUpdatedUser.getRole()).isEqualTo(UserRole.ADMINISTRATOR);
    }

    @Test
    void delete() {
        User user = getUser("testEmail");
        session.persist(user);
        session.flush();
        session.clear();

        session.remove(user);
        session.flush();
        User actualUser = session.find(User.class, user.getId());

        assertThat(actualUser).isNull();
    }
}
