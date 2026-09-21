package com.example.aurastream.repository;

import com.example.aurastream.model.entity.ProviderSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProviderSubscriptionRepository extends JpaRepository<ProviderSubscription, UUID>,
        JpaSpecificationExecutor<ProviderSubscription> {
}
