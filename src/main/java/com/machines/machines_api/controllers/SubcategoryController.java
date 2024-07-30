package com.machines.machines_api.controllers;

import com.machines.machines_api.models.dto.request.SubcategoryRequestDTO;
import com.machines.machines_api.models.dto.response.SubcategoryResponseDTO;
import com.machines.machines_api.models.dto.response.admin.SubcategoryAdminResponseDTO;
import com.machines.machines_api.services.SubcategoryService;
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
@RequestMapping("/api/v1/subcategories")
public class SubcategoryController {
    private final SubcategoryService subcategoryService;

    @GetMapping("/all")
    public ResponseEntity<List<SubcategoryResponseDTO>> getAll() {
        return ResponseEntity.ok(subcategoryService.getAll());
    }

    @GetMapping("/all/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<SubcategoryAdminResponseDTO>> getAllAdmin() {
        List<SubcategoryAdminResponseDTO> categories = subcategoryService.getAllAdmin();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubcategoryResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(subcategoryService.getById(id));
    }

    @GetMapping("/{id}/admin")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SubcategoryAdminResponseDTO> getByIdAdmin(@PathVariable UUID id) {
        return ResponseEntity.ok(subcategoryService.getByIdAdmin(id));
    }

    @PostMapping("/create")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SubcategoryResponseDTO> create(@Valid @RequestBody SubcategoryRequestDTO subcategoryRequestDTO) {
        SubcategoryResponseDTO subcategoryResponseDTO = subcategoryService.create(subcategoryRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(subcategoryResponseDTO);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SubcategoryResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody SubcategoryRequestDTO subcategoryRequestDTO) {
        return ResponseEntity.ok(subcategoryService.update(id, subcategoryRequestDTO));
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        subcategoryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
