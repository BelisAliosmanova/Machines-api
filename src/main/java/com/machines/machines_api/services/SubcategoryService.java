package com.machines.machines_api.services;

import com.machines.machines_api.models.dto.request.SubcategoryRequestDTO;
import com.machines.machines_api.models.dto.response.SubcategoryResponseDTO;
import com.machines.machines_api.models.dto.response.admin.SubcategoryAdminResponseDTO;

import java.util.List;
import java.util.UUID;

public interface SubcategoryService {
    List<SubcategoryResponseDTO> getAll();

    List<SubcategoryAdminResponseDTO> getAllAdmin();

    SubcategoryResponseDTO getById(UUID id);

    SubcategoryAdminResponseDTO getByIdAdmin(UUID id);

    SubcategoryResponseDTO create(SubcategoryRequestDTO subcategoryDTO);

    SubcategoryResponseDTO update(UUID id, SubcategoryRequestDTO subcategoryDTO);

    void delete(UUID id);
}
