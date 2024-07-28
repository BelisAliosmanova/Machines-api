package com.machines.machines_api.services.impl;

import com.machines.machines_api.exceptions.offer.OfferNotFoundException;
import com.machines.machines_api.models.dto.auth.PublicUserDTO;
import com.machines.machines_api.models.dto.request.OfferRequestDTO;
import com.machines.machines_api.models.dto.response.OfferResponseDTO;
import com.machines.machines_api.models.entity.*;
import com.machines.machines_api.repositories.OfferRepository;
import com.machines.machines_api.services.CityService;
import com.machines.machines_api.services.FileService;
import com.machines.machines_api.services.OfferService;
import com.machines.machines_api.services.SubcategoryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OfferServiceImpl implements OfferService {
    private final FileService fileService;
    private final CityService cityService;
    private final SubcategoryService subcategoryService;
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<OfferResponseDTO> getAll() {
        List<Offer> offers = offerRepository.findAllByDeletedAtIsNullOrderByCreatedAtDesc();
        return offers.stream().map(x -> modelMapper.map(x, OfferResponseDTO.class)).toList();
    }

    @Override
    public OfferResponseDTO getById(UUID id) {
        return modelMapper.map(getEntityById(id), OfferResponseDTO.class);
    }

    @Override
    public OfferResponseDTO create(OfferRequestDTO offerRequestDTO) {
        // TODO: verify that main pic is in the pic list

        Subcategory subcategory = subcategoryService.getSubCategoryEntityById(offerRequestDTO.getSubcategoryId());
        City city = cityService.getEntityById(offerRequestDTO.getCityId());
        File mainPicture = fileService.getEntityById(offerRequestDTO.getMainPictureId());
        Set<File> pictures = offerRequestDTO.getPictureIds().stream().map(fileService::getEntityById).collect(Collectors.toSet());

        Offer offer = modelMapper.map(offerRequestDTO, Offer.class);
        offer.setSubcategory(subcategory);
        offer.setCity(city);
        offer.setMainPicture(mainPicture);
        offer.setPictures(pictures);
        offer.setId(null);

        Offer savedOffer = offerRepository.save(offer);
        return modelMapper.map(savedOffer, OfferResponseDTO.class);
    }

    @Override
    public OfferResponseDTO update(UUID id, OfferRequestDTO offerRequestDTO, PublicUserDTO user) {
        return null;
    }

    @Override
    public void delete(UUID id, PublicUserDTO user) {

    }

    @Override
    public Offer getEntityById(UUID id) {
        Optional<Offer> offer = offerRepository.findByIdAndDeletedAtIsNull(id);

        if (offer.isEmpty()) {
            throw new OfferNotFoundException();
        }

        return offer.get();
    }
}
