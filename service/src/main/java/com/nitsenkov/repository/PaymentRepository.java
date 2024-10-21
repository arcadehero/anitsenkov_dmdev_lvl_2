package com.nitsenkov.repository;

import com.nitsenkov.entity.Payment;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class PaymentRepository extends RepositoryBase<UUID, Payment> {

    @Autowired
    public PaymentRepository(EntityManager entityManager) {
        super(Payment.class, entityManager);
    }
}
