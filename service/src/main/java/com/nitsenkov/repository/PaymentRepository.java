package com.nitsenkov.repository;

import com.nitsenkov.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<UUID, Payment> {
}
