package com.nitsenkov.entity;

import com.nitsenkov.BaseIntegrationTest;
import com.nitsenkov.entity.enums.Currency;
import com.nitsenkov.util.TestObjectsBuilder;
import org.junit.jupiter.api.Test;

import static com.nitsenkov.util.TestObjectsBuilder.getAccount;
import static com.nitsenkov.util.TestObjectsBuilder.getUser;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentTestIT extends BaseIntegrationTest {

    @Test
    void create() {
        Account account1 = getAccount(getUser("email1"));
        Account account2 = getAccount(getUser("email2"));
        Payment payment = TestObjectsBuilder.getPayment(account1, account2);

        session.persist(payment);
        session.flush();
        session.clear();

        assertThat(session.get(Payment.class, payment.getId())).isNotNull();
    }

    @Test
    void read() {
        Account account1 = getAccount(getUser("email1"));
        Account account2 = getAccount(getUser("email2"));
        Payment payment = TestObjectsBuilder.getPayment(account1, account2);

        session.persist(payment);
        session.flush();
        session.clear();
        Payment actualPayment = session.find(Payment.class, payment.getId());

        assertThat(actualPayment.getRecipientAccount()).isEqualTo(payment.getRecipientAccount());
    }

    @Test
    void update() {
        Account account1 = getAccount(getUser("email1"));
        Account account2 = getAccount(getUser("email2"));
        Payment payment = TestObjectsBuilder.getPayment(account1, account2);

        session.persist(payment);
        session.flush();
        session.clear();
        payment.setCurrency(Currency.UAE);
        session.merge(payment);
        session.flush();
        session.clear();
        Payment actualPayment = session.find(Payment.class, payment.getId());

        assertThat(actualPayment.getCurrency()).isEqualTo(Currency.UAE);
    }

    @Test
    void delete() {
        Account account1 = getAccount(getUser("email1"));
        Account account2 = getAccount(getUser("email2"));
        Payment payment = TestObjectsBuilder.getPayment(account1, account2);
        session.persist(payment);
        session.flush();
        session.clear();

        session.remove(payment);
        session.flush();
        Payment actualPayment = session.find(Payment.class, payment.getId());

        assertThat(actualPayment).isNull();
    }
}