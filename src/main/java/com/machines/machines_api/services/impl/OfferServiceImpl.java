package com.machines.machines_api.services.impl;

import com.machines.machines_api.models.dto.auth.PublicUserDTO;
import com.machines.machines_api.models.dto.request.OfferRequestDTO;
import com.machines.machines_api.models.dto.response.OfferResponseDTO;
import com.machines.machines_api.models.entity.Country;
import com.machines.machines_api.services.OfferService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OfferServiceImpl implements OfferService {
    @Override
    public List<OfferResponseDTO> getAll() {
        return null;
    }

    @Override
    public OfferResponseDTO getById(UUID id) {
        return null;
    }

    @Override
    public OfferResponseDTO create(OfferRequestDTO offerRequestDTO) {
        return null;
    }

    @Override
    public OfferResponseDTO update(UUID id, OfferRequestDTO offerRequestDTO, PublicUserDTO user) {
        return null;
    }

    @Override
    public void delete(UUID id, PublicUserDTO user) {

    }

    @Override
    public Country getEntityById(UUID id) {
        return null;
    }
}
