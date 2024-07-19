package com.machines.machines_api.services.impl;

import com.machines.machines_api.exceptions.category.CategoryCreateException;
import com.machines.machines_api.exceptions.category.CategoryNotFoundException;
import com.machines.machines_api.models.dto.request.SubcategoryRequestDTO;
import com.machines.machines_api.models.dto.response.CategoryResponseDTO;
import com.machines.machines_api.models.dto.response.SubcategoryResponseDTO;
import com.machines.machines_api.models.entity.Category;
import com.machines.machines_api.models.entity.Subcategory;
import com.machines.machines_api.repositories.SubcategoryRepository;
import com.machines.machines_api.services.CategoryService;
import com.machines.machines_api.services.SubcategoryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubcategoryServiceImpl implements SubcategoryService {
    private final CategoryService categoryService;
    private final SubcategoryRepository subcategoryRepository;
    private final MessageSource messageSource;
    private final ModelMapper modelMapper;

    @Override
    public List<SubcategoryResponseDTO> getAll() {
        List<Subcategory> subcategories = subcategoryRepository.findAllByDeletedAtIsNull();
        return subcategories.stream().map(x -> modelMapper.map(x, SubcategoryResponseDTO.class)).toList();
    }

    @Override
    public SubcategoryResponseDTO getById(UUID id) {
        Subcategory subcategory = getSubCategoryEntityById(id);
        return modelMapper.map(subcategory, SubcategoryResponseDTO.class);
    }

    @Override
    public SubcategoryResponseDTO create(SubcategoryRequestDTO subcategoryDTO) {
        // Make sure subcategory is unique
        if (subcategoryRepository.findByNameAndDeletedAtIsNull(subcategoryDTO.getName()).isPresent()) {
            throw new CategoryCreateException(messageSource, true);
        }

        Category mainCategory = categoryService.getCategoryEntityById(subcategoryDTO.getCategoryId());
        Subcategory subcategory = modelMapper.map(subcategoryDTO, Subcategory.class);
        subcategory.setCategory(mainCategory);
        subcategory.setId(null);

        // Persist subcategory
        try {
            Subcategory savedSubcategory = subcategoryRepository.save(subcategory);
            return modelMapper.map(savedSubcategory, SubcategoryResponseDTO.class);
        } catch (RuntimeException exception) {
            throw new CategoryCreateException(messageSource, false);
        }
    }

    @Override
    public SubcategoryResponseDTO update(UUID id, SubcategoryRequestDTO subcategoryDTO) {
        Subcategory subcategory = getSubCategoryEntityById(id);
        Optional<Subcategory> potentialSubcategory = subcategoryRepository.findByNameAndDeletedAtIsNull(subcategoryDTO.getName());

        if (potentialSubcategory.isPresent() && !subcategory.getId().equals(potentialSubcategory.get().getId())) {
            throw new CategoryCreateException(messageSource, true);
        }

        if (subcategoryDTO.getName() != null) {
            subcategory.setName(subcategoryDTO.getName());
        }

        if (subcategoryDTO.getCategoryId() != null) {
            Category category = categoryService.getCategoryEntityById(subcategoryDTO.getCategoryId());
            subcategory.setCategory(category);
        }

        Subcategory updatedSubcategory = subcategoryRepository.save(subcategory);
        return modelMapper.map(updatedSubcategory, SubcategoryResponseDTO.class);
    }

    @Override
    public void delete(UUID id) {
        Subcategory subcategory = getSubCategoryEntityById(id);
        subcategory.setDeletedAt(LocalDateTime.now());
        subcategoryRepository.save(subcategory);
    }

    public Subcategory getSubCategoryEntityById(UUID id) {
        Optional<Subcategory> subcategory = subcategoryRepository.findByIdAndDeletedAtIsNull(id);

        if (subcategory.isEmpty()) {
            throw new CategoryNotFoundException(messageSource);
        }

        return subcategory.get();
    }
}
