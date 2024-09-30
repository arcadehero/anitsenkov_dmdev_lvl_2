package com.nitsenkov.entity;

import com.nitsenkov.BaseIntegrationTest;
import com.nitsenkov.entity.enums.CardStatus;
import org.junit.jupiter.api.Test;

import static com.nitsenkov.util.TestObjectsBuilder.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CardTestIT extends BaseIntegrationTest {

    @Test
    void create() {
        Card card = getCard(getAccount(getUser("email")));
        session.persist(card);
        session.flush();
        session.clear();

        assertThat(session.get(Card.class, card.getId())).isNotNull();
    }

    @Test
    void read() {
        Card card = getCard(getAccount(getUser("email")));

        session.persist(card);
        session.flush();
        session.clear();
        Card actualCard = session.get(Card.class, card.getId());

        assertThat(actualCard.getId()).isEqualTo(card.getId());
    }

    @Test
    void update() {
        Card card = getCard(getAccount(getUser("email")));
        session.persist(card);
        session.flush();
        session.clear();

        card.setStatus(CardStatus.CLOSED);
        session.merge(card);
        session.flush();
        session.clear();
        Card actualCard = session.get(Card.class, card.getId());

        assertThat(actualCard.getStatus()).isEqualTo(card.getStatus());
    }

    @Test
    void delete() {
        Card card = getCard(getAccount(getUser("email")));
        session.persist(card);
        session.flush();
        session.clear();

        session.remove(card);
        session.flush();
        Card actualCard = session.find(Card.class, card.getId());

        assertThat(actualCard).isNull();
    }
}