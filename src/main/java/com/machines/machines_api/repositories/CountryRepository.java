package com.machines.machines_api.repositories;

import com.machines.machines_api.models.entity.Category;
import com.machines.machines_api.models.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CountryRepository extends JpaRepository<Country, UUID> {
    List<Country> findAllByDeletedAtIsNull();
    Optional<Country> findByIdAndDeletedAtIsNull(UUID id);
    Optional<Country> findByNameAndDeletedAtIsNull(String name);
}
