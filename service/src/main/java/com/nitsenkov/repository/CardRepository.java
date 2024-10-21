package com.nitsenkov.repository;

import com.nitsenkov.entity.Card;
import jakarta.persistence.EntityManager;

import java.util.UUID;

public class CardRepository extends RepositoryBase<UUID, Card> {

    public CardRepository(EntityManager entityManager) {
        super(Card.class, entityManager);
    }
}
