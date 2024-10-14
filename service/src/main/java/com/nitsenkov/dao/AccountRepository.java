package com.nitsenkov.dao;

import com.nitsenkov.entity.Account;
import jakarta.persistence.EntityManager;

import java.util.UUID;

public class AccountRepository extends RepositoryBase<UUID, Account> {

    public AccountRepository(EntityManager entityManager) {
        super(Account.class, entityManager);
    }
}
