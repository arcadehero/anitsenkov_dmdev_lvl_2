package com.nitsenkov.integration.dao;

import com.nitsenkov.entity.Account;
import com.nitsenkov.entity.Card;
import com.nitsenkov.entity.User;
import com.nitsenkov.entity.enums.CardStatus;
import com.nitsenkov.integration.BaseIntegrationTest;
import com.nitsenkov.repository.CardRepository;
import com.nitsenkov.util.TestObjectsBuilder;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
class CardRepositoryIT extends BaseIntegrationTest {

    private final CardRepository cardRepository;

    private Card card;
    private Account account;
    private User user;

    @BeforeEach
    void setUp() {
        user = TestObjectsBuilder.getUser("testEmail");
        account = TestObjectsBuilder.getAccount(user);
        card = TestObjectsBuilder.getCard(account);
    }

    @Test
    void saveCard() {
        Card savedCard = cardRepository.save(card);
        session.clear();
        Optional<Card> actualCard = cardRepository.findById(savedCard.getId());

        actualCard.ifPresent(c -> assertThat(c).isEqualTo(card));
    }

    @Test
    void updateCard() {
        Card savedCard = cardRepository.save(card);
        card.setStatus(CardStatus.CLOSED);

        cardRepository.save(card);
        session.clear();
        Optional<Card> actualCard = cardRepository.findById(savedCard.getId());

        actualCard.ifPresent(c -> Assertions.assertThat(c.getStatus()).isEqualTo(CardStatus.CLOSED));
    }

    @Test
    void deleteCard() {
        Card savedCard = cardRepository.save(card);

        cardRepository.delete(savedCard);
        Optional<Card> actualCard = cardRepository.findById(savedCard.getId());

        assertThat(actualCard.isEmpty()).isTrue();
    }

    @Test
    void findCardById() {
        User user1 = TestObjectsBuilder.getUser("testEmail1");
        Account account1 = TestObjectsBuilder.getAccount(user1);
        Card card1 = TestObjectsBuilder.getCard(account1);
        User user2 = TestObjectsBuilder.getUser("testEmail2");
        Account account2 = TestObjectsBuilder.getAccount(user2);
        Card card2 = TestObjectsBuilder.getCard(account2);
        User user3 = TestObjectsBuilder.getUser("testEmail3");
        Account account3 = TestObjectsBuilder.getAccount(user3);
        Card card3 = TestObjectsBuilder.getCard(account3);
        card3.setStatus(CardStatus.CLOSED);
        cardRepository.save(card);
        cardRepository.save(card1);
        cardRepository.save(card2);
        Card savedCard = cardRepository.save(card3);
        session.clear();

        Optional<Card> actualCard = cardRepository.findById(savedCard.getId());

        actualCard.ifPresent(c -> Assertions.assertThat(c.getStatus()).isEqualTo(card3.getStatus()));
    }
}