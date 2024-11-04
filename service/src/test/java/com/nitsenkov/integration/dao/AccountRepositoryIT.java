package com.nitsenkov.integration.dao;

import com.nitsenkov.integration.BaseIntegrationTest;
import com.nitsenkov.entity.Account;
import com.nitsenkov.entity.User;
import com.nitsenkov.entity.enums.AccountType;
import com.nitsenkov.util.TestObjectsBuilder;
import com.nitsenkov.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class AccountRepositoryIT extends BaseIntegrationTest {

    private final AccountRepository accountRepository;
    private User user;
    private Account account;

    @BeforeEach
    void setUp() {
        user = TestObjectsBuilder.getUser("testEmail");
        account = TestObjectsBuilder.getAccount(user);
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
        User user1 = TestObjectsBuilder.getUser("testEmail1");
        Account account1 = TestObjectsBuilder.getAccount(user1);
        User user2 = TestObjectsBuilder.getUser("testEmail2");
        Account account2 = TestObjectsBuilder.getAccount(user2);
        User user3 = TestObjectsBuilder.getUser("testEmail3");
        Account account3 = TestObjectsBuilder.getAccount(user3);
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