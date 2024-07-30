package com.machines.machines_api.controllers;

import com.machines.machines_api.models.dto.request.CountryRequestDTO;
import com.machines.machines_api.models.dto.response.CountryResponseDTO;
import com.machines.machines_api.models.dto.response.admin.CountryAdminResponseDTO;
import com.machines.machines_api.services.CountryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/countries")
public class CountryController {
    private final CountryService countryService;

    @GetMapping("/all")
    public ResponseEntity<List<CountryResponseDTO>> getAll(@RequestParam(defaultValue = "false") boolean includeRegions) {
        return ResponseEntity.ok(countryService.getAll(includeRegions));
    }

    @GetMapping("/all/admin")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CountryAdminResponseDTO>> getAllAdmin(@RequestParam(defaultValue = "false") boolean includeRegions) {
        List<CountryAdminResponseDTO> countries = countryService.getAllAdmin(includeRegions);
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(countryService.getById(id));
    }

    @GetMapping("/{id}/admin")
    public ResponseEntity<CountryAdminResponseDTO> getByIdAdmin(@PathVariable UUID id) {
        return ResponseEntity.ok(countryService.getCountryByIdAdmin(id));
    }

    @PostMapping("/create")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CountryResponseDTO> create(@Valid @RequestBody CountryRequestDTO countryRequestDTO) {
        CountryResponseDTO countryResponseDTO = countryService.create(countryRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(countryResponseDTO);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CountryResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody CountryRequestDTO countryRequestDTO) {
        return ResponseEntity.ok(countryService.update(id, countryRequestDTO));
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        countryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
