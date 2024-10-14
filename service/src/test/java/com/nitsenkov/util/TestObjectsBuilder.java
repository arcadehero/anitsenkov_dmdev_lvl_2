package com.nitsenkov.util;

import com.nitsenkov.entity.Account;
import com.nitsenkov.entity.Card;
import com.nitsenkov.entity.Payment;
import com.nitsenkov.entity.User;
import com.nitsenkov.entity.enums.AccountStatus;
import com.nitsenkov.entity.enums.AccountType;
import com.nitsenkov.entity.enums.CardStatus;
import com.nitsenkov.entity.enums.CardType;
import com.nitsenkov.entity.enums.Currency;
import com.nitsenkov.entity.enums.PaymentStatus;
import com.nitsenkov.entity.enums.UserRole;

import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.math.BigDecimal.valueOf;

public class TestObjectsBuilder {

    public static Payment getPayment(Account... account) {
        return Payment.builder()
                .senderAccount(account[0])
                .recipientAccount(account[1])
                .currency(Currency.USD)
                .paymentDate(LocalDateTime.now().minusDays(10))
                .status(PaymentStatus.COMPLETED)
                .build();
    }

    public static Card getCard(Account account) {
        return Card.builder()
                .account(account)
                .number("1234_5678")
                .status(CardStatus.ACTIVE)
                .expiryDate(LocalDate.of(2030, 7, 30))
                .type(CardType.DRIVE)
                .build();
    }

    public static Account getAccount(User user) {
        return Account.builder()
                .type(AccountType.DEBIT)
                .user(user)
                .number("123456789")
                .status(AccountStatus.ACTIVE)
                .currency(Currency.EUR)
                .amount(valueOf(100L).setScale(2, RoundingMode.HALF_UP))
                .build();
    }

    public static User getUser(String email) {
        return User.builder()
                .role(UserRole.CLIENT)
                .name("alex")
                .surname("smith")
                .password("qwerty")
                .email(email)
                .build();
    }
}
