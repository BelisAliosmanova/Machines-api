package com.machines.machines_api.repositories;

import com.machines.machines_api.models.entity.Country;
import com.machines.machines_api.models.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RegionRepository extends JpaRepository<Region, UUID> {
    List<Region> findAllByDeletedAtIsNull();
    Optional<Region> findByIdAndDeletedAtIsNull(UUID id);
    Optional<Region> findByNameAndDeletedAtIsNull(String name);
}
