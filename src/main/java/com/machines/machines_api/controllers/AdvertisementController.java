package com.machines.machines_api.controllers;

import com.machines.machines_api.models.entity.Advertisement;
import com.machines.machines_api.services.AdvertisementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/advertisements")
public class AdvertisementController {
    private final AdvertisementService advertisementService;

    @GetMapping("/all")
    public ResponseEntity<List<Advertisement>> getAll() {
        List<Advertisement> advertisements = advertisementService.getAll();
        return ResponseEntity.ok(advertisements);
    }

    @GetMapping("/all/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Advertisement>> getAllAdmin() {
        List<Advertisement> advertisements = advertisementService.getAllAdmin();
        return ResponseEntity.ok(advertisements);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Advertisement> getById(@PathVariable UUID id) {
        Advertisement advertisement = advertisementService.getAdvertisementById(id);
        return ResponseEntity.ok(advertisement);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Advertisement> getByIdAdmin(@PathVariable UUID id) {
        Advertisement advertisement = advertisementService.getAdvertisementByIdAdmin(id);
        return ResponseEntity.ok(advertisement);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Advertisement> create(@Valid @RequestBody Advertisement advertisementRequestDTO) {
        Advertisement advertisement = advertisementService.create(advertisementRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(advertisement);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Advertisement> update(@PathVariable UUID id, @Valid @RequestBody Advertisement advertisementRequestDTO) {
        Advertisement advertisement = advertisementService.update(id, advertisementRequestDTO);
        return ResponseEntity.ok(advertisement);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        advertisementService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}