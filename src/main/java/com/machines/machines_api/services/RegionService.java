package com.machines.machines_api.services;

import com.machines.machines_api.models.dto.request.RegionRequestDTO;
import com.machines.machines_api.models.dto.response.RegionResponseDTO;
import com.machines.machines_api.models.entity.Region;

import java.util.List;
import java.util.UUID;

public interface RegionService {
    List<RegionResponseDTO> getAll();
    RegionResponseDTO getById(UUID id);
    RegionResponseDTO create(RegionRequestDTO regionRequestDTO);
    RegionResponseDTO update(UUID id, RegionRequestDTO regionRequestDTO);
    void delete(UUID id);
    Region getEntityById(UUID id);
}
