package com.machines.machines_api.controllers;

import com.machines.machines_api.enums.OfferSaleType;
import com.machines.machines_api.enums.OfferSort;
import com.machines.machines_api.enums.OfferState;
import com.machines.machines_api.enums.OfferType;
import com.machines.machines_api.interfaces.CheckoutProduct;
import com.machines.machines_api.models.dto.auth.PublicUserDTO;
import com.machines.machines_api.models.dto.common.OfferTypeDTO;
import com.machines.machines_api.models.dto.common.ProductDTO;
import com.machines.machines_api.models.dto.request.OfferRequestDTO;
import com.machines.machines_api.models.dto.response.OfferResponseDTO;
import com.machines.machines_api.models.dto.response.admin.OfferAdminResponseDTO;
import com.machines.machines_api.models.dto.response.admin.OfferSingleAdminResponseDTO;
import com.machines.machines_api.models.dto.specifications.OfferSpecificationDTO;
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
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/offers")
public class OfferController {
    private final OfferService offerService;

    @GetMapping("/types")
    public ResponseEntity<List<OfferTypeDTO>> getOfferTypes() {
        return ResponseEntity.ok(offerService.getOfferTypesAsProducts());
    }

    @GetMapping("/all")
    public ResponseEntity<Page<OfferResponseDTO>> getAll(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) UUID subcategoryId,
            @RequestParam(required = false) UUID cityId,
            @RequestParam(required = false) OfferState offerState,
            @RequestParam(required = false) OfferSaleType offerSaleType,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Boolean bulgarian,
            @RequestParam(required = false, defaultValue = "def") OfferSort offerSort
    ) {
        OfferSpecificationDTO offerSpecificationDTO = OfferSpecificationDTO
                .builder()
                .search(search)
                .subcategoryId(subcategoryId)
                .cityId(cityId)
                .offerState(offerState)
                .offerSaleType(offerSaleType)
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .bulgarian(bulgarian)
                .offerSort(offerSort)
                .build();

        Page<OfferResponseDTO> offers = offerService.getAll(page, size, offerSpecificationDTO);
        return ResponseEntity.ok(offers);
    }

    @GetMapping("/all/user")
    public ResponseEntity<Page<OfferAdminResponseDTO>> getAllForLoggedUser(@RequestParam int page, @RequestParam int size,
                                                                      HttpServletRequest httpServletRequest) {
        PublicUserDTO user = (PublicUserDTO) httpServletRequest.getAttribute(JwtAuthenticationFilter.USER_KEY);
        Page<OfferAdminResponseDTO> offers = offerService.getAllForLoggedUser(page, size, user);
        return ResponseEntity.ok(offers);
    }

    @GetMapping("/all/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<OfferAdminResponseDTO>> getAllAdmin(@RequestParam int page, @RequestParam int size) {
        Page<OfferAdminResponseDTO> offers = offerService.getAllAdmin(page, size);
        return ResponseEntity.ok(offers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferResponseDTO> getById(@PathVariable UUID id, HttpServletRequest request) {
        PublicUserDTO user = (PublicUserDTO) request.getAttribute(JwtAuthenticationFilter.USER_KEY);
        OfferResponseDTO offer = offerService.getById(id, user);
        return ResponseEntity.ok(offer);
    }

    @GetMapping("/{id}/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OfferSingleAdminResponseDTO> getByIdAdmin(@PathVariable UUID id) {
        return ResponseEntity.ok(offerService.getByIdAdmin(id));
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('user:create')")
    public ResponseEntity<OfferResponseDTO> create(
            @RequestBody OfferRequestDTO offerRequestDTO,
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
