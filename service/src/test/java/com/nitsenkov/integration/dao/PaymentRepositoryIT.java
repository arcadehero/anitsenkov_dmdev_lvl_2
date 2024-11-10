package com.nitsenkov.integration.dao;

import com.nitsenkov.integration.BaseIntegrationTest;
import com.nitsenkov.entity.Account;
import com.nitsenkov.entity.Payment;
import com.nitsenkov.entity.enums.Currency;
import com.nitsenkov.repository.PaymentRepository;
import com.nitsenkov.util.TestObjectsBuilder;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class PaymentRepositoryIT extends BaseIntegrationTest {

    private Account firstAccount;
    private Account secondAccount;
    private Account thirdAccount;
    private Account fourthAccount;
    private Payment payment;
    private Payment payment1;
    private final PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        firstAccount = TestObjectsBuilder.getAccount(TestObjectsBuilder.getUser("email1"));
        secondAccount = TestObjectsBuilder.getAccount(TestObjectsBuilder.getUser("email2"));
        payment = TestObjectsBuilder.getPayment(firstAccount, secondAccount);
    }

    @Test
    void savePayment() {
        Payment savedPayment = paymentRepository.save(payment);

        session.clear();
        Optional<Payment> actualPayment = paymentRepository.findById(savedPayment.getId());
        assertThat(actualPayment).isNotNull();
    }

    @Test
    void updatePayment() {
        Payment startPayment = paymentRepository.save(payment);
        payment.setCurrency(Currency.RUB);

        paymentRepository.save(payment);

        session.clear();
        Optional<Payment> actualPayment = paymentRepository.findById(startPayment.getId());
        actualPayment.ifPresent(p -> assertThat(p.getCurrency()).isEqualTo(Currency.RUB));
    }

    @Test
    void deletePayment() {
        Payment startPayment = paymentRepository.save(payment);

        paymentRepository.delete(startPayment);
        Optional<Payment> actualPayment = paymentRepository.findById(startPayment.getId());

        assertThat(actualPayment.isPresent()).isFalse();
    }

    @Test
    void findPaymentById() {
        thirdAccount = TestObjectsBuilder.getAccount(TestObjectsBuilder.getUser("email3"));
        fourthAccount = TestObjectsBuilder.getAccount(TestObjectsBuilder.getUser("email4"));
        payment1 = TestObjectsBuilder.getPayment(thirdAccount, fourthAccount);
        Payment startPayment = paymentRepository.save(payment);
        Payment startPayment1 = paymentRepository.save(payment1);
        session.clear();

        Optional<Payment> actualPayment = paymentRepository.findById(startPayment1.getId());

        actualPayment.ifPresent(p -> assertThat(p.getId()).isEqualTo(startPayment1.getId()));
    }
}
