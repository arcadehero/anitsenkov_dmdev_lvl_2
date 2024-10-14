package com.nitsenkov.dao;

import com.nitsenkov.entity.Payment;
import jakarta.persistence.EntityManager;

import java.util.UUID;

public class PaymentRepository extends RepositoryBase<UUID, Payment> {

    public PaymentRepository(EntityManager entityManager) {
        super(Payment.class, entityManager);
    }
}
