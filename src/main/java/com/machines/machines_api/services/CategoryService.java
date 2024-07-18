package com.machines.machines_api.services;

import com.machines.machines_api.models.dto.request.CategoryRequestDTO;
import com.machines.machines_api.models.dto.response.CategoryResponseDTO;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<CategoryResponseDTO> getAll();
    CategoryResponseDTO getCategoryById(UUID id);
    CategoryResponseDTO create(CategoryRequestDTO categoryDTO);
    CategoryResponseDTO update(UUID id, CategoryRequestDTO categoryDTO);
    void delete(UUID id);
}
