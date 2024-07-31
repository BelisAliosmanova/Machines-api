package com.machines.machines_api.repositories;

import com.machines.machines_api.models.entity.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OfferRepository extends JpaRepository<Offer, UUID> {
    @Query(value = "SELECT *, " +
            "ts_rank_cd(to_tsvector('simple', o.title), to_tsquery('simple', :searchTerm)) AS score " +
            "FROM offers o " +
            "WHERE o.id != :currentOfferId " +
            "ORDER BY score DESC",
            nativeQuery = true
    )
    List<Offer> findSimilarOffers(@Param("searchTerm") String searchTerm, @Param("currentOfferId") UUID id);

    Page<Offer> findAllByDeletedAtIsNullOrderByCreatedAtDesc(Pageable pageable);

    Optional<Offer> findByIdAndDeletedAtIsNull(UUID id);

    Page<Offer> findAllByDeletedAtIsNull(PageRequest pageRequest);

    Page<Offer> findAllByOwnerId(UUID ownerId, PageRequest pageRequest);

    List<Offer> findAllByOwnerId(UUID ownerId);
}
