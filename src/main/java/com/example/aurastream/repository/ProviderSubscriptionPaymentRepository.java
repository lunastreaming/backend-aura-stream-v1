package com.example.aurastream.repository;

import com.example.aurastream.model.entity.ProviderSubscriptionPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProviderSubscriptionPaymentRepository extends JpaRepository<ProviderSubscriptionPayment, UUID> {
}
