package com.machines.machines_api.repositories;

import com.machines.machines_api.enums.OfferType;
import com.machines.machines_api.models.entity.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<Offer, UUID>, JpaSpecificationExecutor<Offer> {
    @Query(value = "SELECT *, " +
            "ts_rank_cd(to_tsvector('simple', o.title), to_tsquery('simple', :searchTerm)) AS score " +
            "FROM offers o " +
            "WHERE o.id != :currentOfferId " +
            "ORDER BY score DESC",
            nativeQuery = true
    )
    List<Offer> findSimilarOffers(@Param("searchTerm") String searchTerm, @Param("currentOfferId") UUID id);

    Optional<Offer> findByIdAndDeletedAtIsNull(UUID id);

    Page<Offer> findAllByDeletedAtIsNull(PageRequest pageRequest);

    Page<Offer> findAllByOwnerId(UUID ownerId, PageRequest pageRequest);

    List<Offer> findAllByAutoUpdateIsTrue();

    List<Offer> findAllByOfferTypeIsNot(OfferType offerSaleType);
}
