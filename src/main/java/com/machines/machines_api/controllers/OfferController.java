package com.machines.machines_api.controllers;

import com.machines.machines_api.models.dto.auth.PublicUserDTO;
import com.machines.machines_api.models.dto.request.OfferRequestDTO;
import com.machines.machines_api.models.dto.response.OfferResponseDTO;
import com.machines.machines_api.models.dto.response.admin.OfferAdminResponseDTO;
import com.machines.machines_api.security.filters.JwtAuthenticationFilter;
import com.machines.machines_api.services.OfferService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/offers")
public class OfferController {
    private final OfferService offerService;

    @GetMapping("/all")
    public ResponseEntity<Page<OfferResponseDTO>> getAll(@RequestParam int page, @RequestParam int size) {
        Page<OfferResponseDTO> offers = offerService.getAll(page, size);
        return ResponseEntity.ok(offers);
    }

    @GetMapping("/all/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OfferAdminResponseDTO>> getAllAdmin() {
        List<OfferAdminResponseDTO> offers = offerService.getAllAdmin();
        return ResponseEntity.ok(offers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferResponseDTO> getById(@PathVariable UUID id) {
        OfferResponseDTO offer = offerService.getById(id);
        return ResponseEntity.ok(offer);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('user:create')")
    public ResponseEntity<OfferResponseDTO> create(
            @Valid @RequestBody OfferRequestDTO offerRequestDTO,
            HttpServletRequest httpServletRequest
    ) {
        PublicUserDTO user = (PublicUserDTO) httpServletRequest.getAttribute(JwtAuthenticationFilter.USER_KEY);
        OfferResponseDTO offer = offerService.create(offerRequestDTO, user);

        return ResponseEntity.status(HttpStatus.CREATED).body(offer);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('user:update')")
    public ResponseEntity<OfferResponseDTO> update(
            @PathVariable UUID id,
            @Valid @RequestBody OfferRequestDTO offerRequestDTO,
            HttpServletRequest httpServletRequest
    ) {
        PublicUserDTO user = (PublicUserDTO) httpServletRequest.getAttribute(JwtAuthenticationFilter.USER_KEY);
        OfferResponseDTO offer = offerService.update(id, offerRequestDTO, user);

        return ResponseEntity.ok(offer);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:delete')")
    public ResponseEntity<Void> delete(@PathVariable UUID id, HttpServletRequest httpServletRequest) {
        PublicUserDTO user = (PublicUserDTO) httpServletRequest.getAttribute(JwtAuthenticationFilter.USER_KEY);
        offerService.delete(id, user);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
