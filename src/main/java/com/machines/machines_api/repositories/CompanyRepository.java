package com.machines.machines_api.repositories;

import com.machines.machines_api.models.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID>, JpaSpecificationExecutor<Company> {
    Optional<Company> findByIdAndDeletedAtIsNull(UUID id);

    Page<Company> findAllByDeletedAtIsNull(PageRequest pageRequest);

    Page<Company> findAllByOwnerId(UUID ownerId, PageRequest pageRequest);

    List<Company> findAllByOwnerId(UUID ownerId);
}
