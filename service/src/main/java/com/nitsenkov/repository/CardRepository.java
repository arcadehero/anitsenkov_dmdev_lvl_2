package com.nitsenkov.repository;

import com.nitsenkov.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardRepository extends JpaRepository<UUID, Card> {
}
