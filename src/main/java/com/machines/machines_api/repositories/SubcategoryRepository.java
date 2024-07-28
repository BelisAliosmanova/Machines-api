package com.machines.machines_api.repositories;

import com.machines.machines_api.models.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubcategoryRepository extends JpaRepository<Subcategory, UUID> {
    List<Subcategory> findAllByDeletedAtIsNull();

    Optional<Subcategory> findByIdAndDeletedAtIsNull(UUID id);

    Optional<Subcategory> findByNameAndDeletedAtIsNull(String name);
}
