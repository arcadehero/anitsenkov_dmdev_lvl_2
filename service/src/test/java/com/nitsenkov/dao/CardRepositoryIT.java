package com.nitsenkov.dao;

import com.nitsenkov.BaseIntegrationTest;
import com.nitsenkov.entity.Account;
import com.nitsenkov.entity.Card;
import com.nitsenkov.entity.User;
import com.nitsenkov.entity.enums.CardStatus;
import com.nitsenkov.repository.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.nitsenkov.util.TestObjectsBuilder.getAccount;
import static com.nitsenkov.util.TestObjectsBuilder.getCard;
import static com.nitsenkov.util.TestObjectsBuilder.getUser;
import static org.assertj.core.api.Assertions.assertThat;

class CardRepositoryIT extends BaseIntegrationTest {

    private CardRepository cardRepository;

    private Card card;
    private Account account;
    private User user;

    @BeforeEach
    void setUp() {
        cardRepository = new CardRepository(session);
        user = getUser("testEmail");
        account = getAccount(user);
        card = getCard(account);
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

        cardRepository.update(card);
        session.clear();
        Optional<Card> actualCard = cardRepository.findById(savedCard.getId());

        actualCard.ifPresent(c -> assertThat(c.getStatus()).isEqualTo(CardStatus.CLOSED));
    }

    @Test
    void deleteCard() {//вопросики по cascade
        Card savedCard = cardRepository.save(card);

        cardRepository.delete(savedCard);
        Optional<Card> actualCard = cardRepository.findById(savedCard.getId());

        assertThat(actualCard.isEmpty()).isTrue();
    }

    @Test
    void findCardById() {
        User user1 = getUser("testEmail1");
        Account account1 = getAccount(user1);
        Card card1 = getCard(account1);
        User user2 = getUser("testEmail2");
        Account account2 = getAccount(user2);
        Card card2 = getCard(account2);
        User user3 = getUser("testEmail3");
        Account account3 = getAccount(user3);
        Card card3 = getCard(account3);
        card3.setStatus(CardStatus.CLOSED);
        cardRepository.save(card);
        cardRepository.save(card1);
        cardRepository.save(card2);
        Card savedCard = cardRepository.save(card3);
        session.clear();

        Optional<Card> actualCard = cardRepository.findById(savedCard.getId());

        actualCard.ifPresent(c -> assertThat(c.getStatus()).isEqualTo(card3.getStatus()));
    }
}