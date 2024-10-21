package com.nitsenkov.repository;

import com.nitsenkov.entity.Account;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class AccountRepository extends RepositoryBase<UUID, Account> {

    @Autowired
    public AccountRepository(EntityManager entityManager) {
        super(Account.class, entityManager);
    }
}
