package com.machines.machines_api.repositories;

import com.machines.machines_api.models.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OfferRepository extends JpaRepository<Offer, UUID> {
    List<Offer> findAllByDeletedAtIsNullOrderByCreatedAt();
    Optional<Offer> findByIdAndDeletedAtIsNull(UUID id);
    Optional<Offer> findByTitleAndDeletedAtIsNull(String title);
}
