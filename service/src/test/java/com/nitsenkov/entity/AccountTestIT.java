package com.nitsenkov.entity;

import com.nitsenkov.BaseIntegrationTest;
import com.nitsenkov.entity.enums.AccountType;
import org.junit.jupiter.api.Test;

import static com.nitsenkov.util.TestObjectsBuilder.getAccount;
import static com.nitsenkov.util.TestObjectsBuilder.getUser;
import static org.assertj.core.api.Assertions.assertThat;

class AccountTestIT extends BaseIntegrationTest {

    @Test
    void create() {
        Account account = getAccount(getUser("email"));

        session.persist(account);
        session.flush();
        session.clear();

        assertThat(session.get(Account.class, account.getId())).isNotNull();
    }
    
    @Test
    void read(){
        Account expectedAccount = getAccount(getUser("email"));
        session.persist(expectedAccount);
        session.flush();
        session.clear();

        Account actualAccount = session.find(Account.class, expectedAccount.getId());

        assertThat(actualAccount.getId()).isEqualTo(expectedAccount.getId());
    }

    @Test
    void update(){
        Account expectedAccount = getAccount(getUser("email"));
        session.persist(expectedAccount);
        session.flush();
        session.clear();
        expectedAccount.setType(AccountType.CREDIT);

        session.merge(expectedAccount);
        session.flush();
        session.clear();
        Account actualUpdatedAccount = session.find(Account.class, expectedAccount.getId());

        assertThat(actualUpdatedAccount.getType()).isEqualTo(expectedAccount.getType());
    }


    @Test
    void delete(){
        Account expectedAccount = getAccount(getUser("email"));
        session.persist(expectedAccount);
        session.flush();
        session.clear();

        session.remove(expectedAccount);
        session.flush();
        Account actualAccount = session.find(Account.class, expectedAccount.getId());

        assertThat(actualAccount).isNull();
    }
}