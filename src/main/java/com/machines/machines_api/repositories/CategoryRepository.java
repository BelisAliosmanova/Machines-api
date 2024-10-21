package com.machines.machines_api.repositories;

import com.machines.machines_api.models.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    List<Category> findAllByDeletedAtIsNull();

    Optional<Category> findByIdAndDeletedAtIsNull(UUID id);

    Optional<Category> findByNameAndDeletedAtIsNull(String name);

    @Query("SELECT c FROM Category c WHERE c.deletedAt IS NULL AND EXISTS (SELECT o FROM Offer o WHERE o.category = c AND o.deletedAt IS NULL)")
    List<Category> findAllWithActiveOffers();
}
