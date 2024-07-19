package com.machines.machines_api.services.impl;

import com.machines.machines_api.models.dto.request.RegionRequestDTO;
import com.machines.machines_api.models.dto.response.RegionResponseDTO;
import com.machines.machines_api.models.entity.Region;
import com.machines.machines_api.services.RegionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RegionServiceImpl implements RegionService {
    @Override
    public List<RegionResponseDTO> getAll() {
        return null;
    }

    @Override
    public RegionResponseDTO getById(UUID id) {
        return null;
    }

    @Override
    public RegionResponseDTO create(RegionRequestDTO regionRequestDTO) {
        return null;
    }

    @Override
    public RegionResponseDTO update(UUID id, RegionRequestDTO regionRequestDTO) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public Region getEntityById(UUID id) {
        return null;
    }
}
