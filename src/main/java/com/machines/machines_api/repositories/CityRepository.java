package com.machines.machines_api.repositories;

import com.machines.machines_api.models.entity.City;
import com.machines.machines_api.models.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<City, UUID> {
    List<City> findAllByDeletedAtIsNull();
    Optional<City> findByIdAndDeletedAtIsNull(UUID id);
    Optional<City> findByNameAndDeletedAtIsNull(String name);
}
