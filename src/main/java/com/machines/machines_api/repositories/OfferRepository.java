package com.machines.machines_api.repositories;

import com.machines.machines_api.enums.OfferType;
import com.machines.machines_api.models.entity.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Query("SELECT o FROM Offer o WHERE o.offerType IN :offerTypes AND o.deletedAt IS NULL")
    Page<Offer> findAllByOfferTypeInAndDeletedAtIsNull(@Param("offerTypes") List<OfferType> offerTypes, Pageable pageable);

    Optional<Offer> findByIdAndDeletedAtIsNull(UUID id);

    Page<Offer> findAllByDeletedAtIsNull(PageRequest pageRequest);

    Page<Offer> findAllByOwnerId(UUID ownerId, PageRequest pageRequest);

    List<Offer> findAllByAutoUpdateIsTrue();

    List<Offer> findAllByOfferTypeIsNot(OfferType offerSaleType);

    List<Offer> findAllByOfferTypeAndDeletedAtIsNullOrderByCreatedAtDesc(OfferType offerType);

    @Query("SELECT o FROM Offer o WHERE o.deletedAt IS NULL ORDER BY " +
            "CASE WHEN o.offerType = 'TOP' THEN 1 " +
            "WHEN o.offerType = 'VIP' THEN 2 " +
            "WHEN o.offerType = 'BASIC' THEN 3 ELSE 4 END, " +
            "o.createdAt DESC")
    Page<Offer> findAllOffersWithCustomSort(Pageable pageable);
}
