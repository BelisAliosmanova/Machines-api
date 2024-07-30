package com.machines.machines_api.controllers;

import com.machines.machines_api.models.dto.request.CityRequestDTO;
import com.machines.machines_api.models.dto.response.CityResponseDTO;
import com.machines.machines_api.models.dto.response.admin.CityAdminResponseDTO;
import com.machines.machines_api.services.CityService;
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
@RequestMapping("/api/v1/cities")
public class CityController {
    private final CityService cityService;

    @GetMapping("/all")
    public ResponseEntity<List<CityResponseDTO>> getAll() {
        return ResponseEntity.ok(cityService.getAll());
    }

    @GetMapping("/all/admin")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CityAdminResponseDTO>> getAllAdmin() {
        List<CityAdminResponseDTO> cities = cityService.getAllAdmin();
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(cityService.getById(id));
    }

    @GetMapping("/{id}/admin")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CityAdminResponseDTO> getByIdAdmin(@PathVariable UUID id) {
        return ResponseEntity.ok(cityService.getCategoryByIdAdmin(id));
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CityResponseDTO> create(@Valid @RequestBody CityRequestDTO cityRequestDTO) {
        CityResponseDTO cityResponseDTO = cityService.create(cityRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cityResponseDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CityResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody CityRequestDTO cityRequestDTO) {
        return ResponseEntity.ok(cityService.update(id, cityRequestDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        cityService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
