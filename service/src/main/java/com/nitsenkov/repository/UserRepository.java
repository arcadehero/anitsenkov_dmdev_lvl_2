package com.nitsenkov.repository;

import com.nitsenkov.entity.User;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UserRepository extends RepositoryBase<UUID, User> {

    @Autowired
    public UserRepository(EntityManager entityManager) {
        super(User.class, entityManager);
    }
}
