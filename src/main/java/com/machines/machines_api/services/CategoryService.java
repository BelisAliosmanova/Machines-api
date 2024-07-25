package com.machines.machines_api.services;

import com.machines.machines_api.models.dto.request.CategoryRequestDTO;
import com.machines.machines_api.models.dto.response.CategoryResponseDTO;
import com.machines.machines_api.models.dto.response.admin.CategoryAdminResponseDTO;
import com.machines.machines_api.models.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<CategoryResponseDTO> getAll();
    List<CategoryAdminResponseDTO> getAllAdmin();
    CategoryResponseDTO getCategoryById(UUID id);
    CategoryResponseDTO create(CategoryRequestDTO categoryDTO);
    CategoryResponseDTO update(UUID id, CategoryRequestDTO categoryDTO);
    void delete(UUID id);
    Category getCategoryEntityById(UUID id);
}
