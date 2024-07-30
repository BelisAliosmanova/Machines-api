package com.machines.machines_api.controllers;

import com.machines.machines_api.models.dto.request.RegionRequestDTO;
import com.machines.machines_api.models.dto.response.RegionResponseDTO;
import com.machines.machines_api.models.dto.response.admin.RegionAdminResponseDTO;
import com.machines.machines_api.services.RegionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/regions")
public class RegionController {
    private final RegionService regionService;

    @GetMapping("/all")
    public ResponseEntity<List<RegionResponseDTO>> getAll() {
        return ResponseEntity.ok(regionService.getAll());
    }

    @GetMapping("/all/admin")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RegionAdminResponseDTO>> getAllAdmin() {
        List<RegionAdminResponseDTO> regions = regionService.getAllAdmin();
        return ResponseEntity.ok(regions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(regionService.getById(id));
    }

    @GetMapping("/{id}/admin")
    public ResponseEntity<RegionAdminResponseDTO> getByIdAdmin(@PathVariable UUID id) {
        return ResponseEntity.ok(regionService.getCountryByIdAdmin(id));
    }

    @PostMapping("/create")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RegionResponseDTO> create(@Valid @RequestBody RegionRequestDTO regionRequestDTO) {
        RegionResponseDTO regionResponseDTO = regionService.create(regionRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(regionResponseDTO);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RegionResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody RegionRequestDTO regionRequestDTO) {
        return ResponseEntity.ok(regionService.update(id, regionRequestDTO));
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        regionService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
