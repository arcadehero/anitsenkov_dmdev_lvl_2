package com.nitsenkov.repository;

import com.nitsenkov.entity.Card;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CardRepository extends RepositoryBase<UUID, Card> {

    public CardRepository(EntityManager entityManager) {
        super(Card.class, entityManager);
    }
}
