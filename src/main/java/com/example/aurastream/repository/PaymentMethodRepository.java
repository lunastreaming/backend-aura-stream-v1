package com.example.aurastream.repository;

import com.example.aurastream.model.PaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethodEntity, UUID> {

    List<PaymentMethodEntity> findByIsActiveTrue();

}
