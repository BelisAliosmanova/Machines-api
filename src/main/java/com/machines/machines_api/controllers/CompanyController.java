package com.machines.machines_api.controllers;

import com.machines.machines_api.enums.CompanySort;
import com.machines.machines_api.models.dto.auth.PublicUserDTO;
import com.machines.machines_api.models.dto.request.CompanyRequestDTO;
import com.machines.machines_api.models.dto.response.CompanyResponseDTO;
import com.machines.machines_api.models.dto.response.admin.CompanyAdminResponseDTO;
import com.machines.machines_api.models.dto.specifications.CompanySpecificationDTO;
import com.machines.machines_api.security.filters.JwtAuthenticationFilter;
import com.machines.machines_api.services.CompanyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/companies")
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/all")
    public ResponseEntity<Page<CompanyResponseDTO>> getAll(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) UUID cityId,
            @RequestParam(required = false, defaultValue = "def") CompanySort companySort
    ) {
        CompanySpecificationDTO companySpecificationDTO = CompanySpecificationDTO
                .builder()
                .search(search)
                .cityId(cityId)
                .companySort(companySort)
                .build();

        Page<CompanyResponseDTO> companies = companyService.getAll(page, size, companySpecificationDTO);
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/all/user")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Page<CompanyAdminResponseDTO>> getAllForLoggedUser(@RequestParam int page, @RequestParam int size,
                                                                             HttpServletRequest httpServletRequest) {
        PublicUserDTO user = (PublicUserDTO) httpServletRequest.getAttribute(JwtAuthenticationFilter.USER_KEY);
        Page<CompanyAdminResponseDTO> companies = companyService.getAllForLoggedUser(page, size, user);
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/all/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<CompanyAdminResponseDTO>> getAllAdmin(@RequestParam int page, @RequestParam int size) {
        Page<CompanyAdminResponseDTO> companies = companyService.getAllAdmin(page, size);
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDTO> getById(@PathVariable UUID id, HttpServletRequest request) {
        PublicUserDTO user = (PublicUserDTO) request.getAttribute(JwtAuthenticationFilter.USER_KEY);
        CompanyResponseDTO company = companyService.getById(id, user);
        return ResponseEntity.ok(company);
    }

    @GetMapping("/{id}/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CompanyAdminResponseDTO> getByIdAdmin(@PathVariable UUID id) {
        return ResponseEntity.ok(companyService.getByIdAdmin(id));
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('user:create')")
    public ResponseEntity<CompanyResponseDTO> create(
            @RequestBody CompanyRequestDTO companyRequestDTO,
            HttpServletRequest httpServletRequest
    ) {
        PublicUserDTO user = (PublicUserDTO) httpServletRequest.getAttribute(JwtAuthenticationFilter.USER_KEY);
        CompanyResponseDTO company = companyService.create(companyRequestDTO, user);

        return ResponseEntity.status(HttpStatus.CREATED).body(company);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('user:update')")
    public ResponseEntity<CompanyResponseDTO> update(
            @PathVariable UUID id,
            @Valid @RequestBody CompanyRequestDTO companyRequestDTO,
            HttpServletRequest httpServletRequest
    ) {
        PublicUserDTO user = (PublicUserDTO) httpServletRequest.getAttribute(JwtAuthenticationFilter.USER_KEY);
        CompanyResponseDTO company = companyService.update(id, companyRequestDTO, user);

        return ResponseEntity.ok(company);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:delete')")
    public ResponseEntity<Void> delete(@PathVariable UUID id, HttpServletRequest httpServletRequest) {
        PublicUserDTO user = (PublicUserDTO) httpServletRequest.getAttribute(JwtAuthenticationFilter.USER_KEY);
        companyService.delete(id, user);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
