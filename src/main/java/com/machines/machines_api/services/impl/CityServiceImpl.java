package com.machines.machines_api.services.impl;

import com.machines.machines_api.models.dto.request.CityRequestDTO;
import com.machines.machines_api.models.dto.response.CityResponseDTO;
import com.machines.machines_api.models.entity.City;
import com.machines.machines_api.services.CityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CityServiceImpl implements CityService {
    @Override
    public List<CityResponseDTO> getAll() {
        return null;
    }

    @Override
    public CityResponseDTO getById(UUID id) {
        return null;
    }

    @Override
    public CityResponseDTO create(CityRequestDTO cityRequestDTO) {
        return null;
    }

    @Override
    public CityResponseDTO update(UUID id, CityRequestDTO cityRequestDTO) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public City getEntityById(UUID id) {
        return null;
    }
}
