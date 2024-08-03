package com.machines.machines_api.services;

import com.machines.machines_api.models.dto.auth.PublicUserDTO;
import com.machines.machines_api.models.dto.request.CompanyRequestDTO;
import com.machines.machines_api.models.dto.response.CompanyResponseDTO;
import com.machines.machines_api.models.dto.response.admin.CompanyAdminResponseDTO;
import com.machines.machines_api.models.entity.Company;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface CompanyService {
    Page<CompanyResponseDTO> getAll(int page, int size);

    Page<CompanyAdminResponseDTO> getAllForLoggedUser(int page, int size, PublicUserDTO user);

    Page<CompanyAdminResponseDTO> getAllAdmin(int page, int size);

    CompanyResponseDTO getById(UUID id, PublicUserDTO user);

    CompanyAdminResponseDTO getByIdAdmin(UUID id);

    CompanyResponseDTO create(CompanyRequestDTO companyRequestDTO, PublicUserDTO user);

    CompanyResponseDTO update(UUID id, CompanyRequestDTO companyRequestDTO, PublicUserDTO user);

    void delete(UUID id, PublicUserDTO user);

    Company getEntityById(UUID id);

    Company getEntityByIdAdmin(UUID id);
}

