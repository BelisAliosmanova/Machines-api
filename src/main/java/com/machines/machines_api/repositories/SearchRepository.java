package com.machines.machines_api.repositories;

import com.machines.machines_api.models.entity.Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SearchRepository extends JpaRepository<Search, UUID> {
    List<Search> findAllByDeletedAtIsNull();

    Optional<Search> findByIdAndDeletedAtIsNull(UUID id);
}
