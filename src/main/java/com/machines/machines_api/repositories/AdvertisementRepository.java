package com.machines.machines_api.repositories;

import com.machines.machines_api.models.entity.Advertisement;
import com.machines.machines_api.models.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, UUID> {
    List<Advertisement> findAllByDeletedAtIsNull();

    Optional<Advertisement> findByIdAndDeletedAtIsNull(UUID id);
}
