package com.nitsenkov.dao;

import com.nitsenkov.BaseIntegrationTest;
import com.nitsenkov.entity.Account;
import com.nitsenkov.entity.User;
import com.nitsenkov.entity.enums.AccountType;
import com.nitsenkov.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.nitsenkov.util.TestObjectsBuilder.getAccount;
import static com.nitsenkov.util.TestObjectsBuilder.getUser;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountRepositoryIT extends BaseIntegrationTest {

    private final AccountRepository accountRepository = context.getBean("accountRepository", AccountRepository.class);
    private User user;
    private Account account;

    @BeforeEach
    void setUp() {
        user = getUser("testEmail");
        account = getAccount(user);
//        accountRepository = context.getBean("accountRepository", AccountRepository.class);
    }

    @Test
    void saveAccount() {
        Account savedAccount = accountRepository.save(account);
        session.clear();
        Optional<Account> actualAccount = accountRepository.findById(savedAccount.getId());

        assertThat(actualAccount.isPresent()).isTrue();
    }


    @Test
    void updateAccount() {
        Account savedAccount = accountRepository.save(account);
        account.setType(AccountType.CREDIT);

        accountRepository.update(account);

        session.clear();
        Optional<Account> actualAccount = accountRepository.findById(savedAccount.getId());
        actualAccount.ifPresent(a -> assertThat(a.getType()).isEqualTo(AccountType.CREDIT));
    }

    @Test
    void deleteAccount() {
        Account savedAccount = accountRepository.save(account);

        accountRepository.delete(savedAccount);

        Optional<Account> actualAccount = accountRepository.findById(savedAccount.getId());
        assertThat(actualAccount).isEmpty();
    }

    @Test
    void findAccountById() {
        User user1 = getUser("testEmail1");
        Account account1 = getAccount(user1);
        User user2 = getUser("testEmail2");
        Account account2 = getAccount(user2);
        User user3 = getUser("testEmail3");
        Account account3 = getAccount(user3);
        account3.setType(AccountType.CREDIT);
        accountRepository.save(account);
        accountRepository.save(account1);
        accountRepository.save(account2);
        Account savedAccount3 = accountRepository.save(account3);
        session.clear();

        Optional<Account> actualAccount = accountRepository.findById(savedAccount3.getId());

        actualAccount.ifPresent(a -> assertThat(a.getType()).isEqualTo(savedAccount3.getType()));
    }
}