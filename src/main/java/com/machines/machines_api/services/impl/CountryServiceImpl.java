package com.machines.machines_api.services.impl;

import com.machines.machines_api.models.dto.request.CountryRequestDTO;
import com.machines.machines_api.models.dto.response.CountryResponseDTO;
import com.machines.machines_api.models.entity.Country;
import com.machines.machines_api.services.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CountryServiceImpl implements CountryService {
    @Override
    public List<CountryResponseDTO> getAll(boolean includeRegions) {
        return null;
    }

    @Override
    public CountryResponseDTO getById(UUID id) {
        return null;
    }

    @Override
    public CountryResponseDTO create(CountryRequestDTO countryRequestDTO) {
        return null;
    }

    @Override
    public CountryResponseDTO update(UUID id, CountryRequestDTO countryRequestDTO) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public Country getEntityById(UUID id) {
        return null;
    }
}
