package com.machines.machines_api.services;

import com.machines.machines_api.models.dto.request.CategoryRequestDTO;
import com.machines.machines_api.models.dto.request.CountryRequestDTO;
import com.machines.machines_api.models.dto.response.CategoryResponseDTO;
import com.machines.machines_api.models.dto.response.CountryResponseDTO;
import com.machines.machines_api.models.entity.Category;
import com.machines.machines_api.models.entity.Country;

import java.util.List;
import java.util.UUID;

public interface CountryService {
    List<CountryResponseDTO> getAll(boolean includeRegions);
    CountryResponseDTO getById(UUID id);
    CountryResponseDTO create(CountryRequestDTO countryRequestDTO);
    CountryResponseDTO update(UUID id, CountryRequestDTO countryRequestDTO);
    void delete(UUID id);
    Country getEntityById(UUID id);
}
