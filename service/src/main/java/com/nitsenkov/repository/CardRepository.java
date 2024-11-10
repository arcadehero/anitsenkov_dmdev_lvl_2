package com.nitsenkov.repository;

import com.nitsenkov.entity.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CardRepository extends CrudRepository<Card, UUID> {
}
