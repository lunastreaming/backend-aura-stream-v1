package com.example.aurastream.repository;

import com.example.aurastream.model.SellerProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SellerProfileRepository extends JpaRepository<SellerProfileEntity, UUID> {
}
