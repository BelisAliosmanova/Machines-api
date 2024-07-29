package com.machines.machines_api.repositories;

import com.machines.machines_api.models.entity.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OfferRepository extends JpaRepository<Offer, UUID> {
    Page<Offer> findAllByDeletedAtIsNullOrderByCreatedAtDesc(Pageable pageable);

    Optional<Offer> findByIdAndDeletedAtIsNull(UUID id);

    Page<Offer> findAllByDeletedAtIsNull(PageRequest pageRequest);
}
