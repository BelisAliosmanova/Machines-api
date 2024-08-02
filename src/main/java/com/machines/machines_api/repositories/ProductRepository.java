package com.machines.machines_api.repositories;

import com.machines.machines_api.models.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByCheckoutIdAndDeletedAtIsNull(String checkoutId);
}
