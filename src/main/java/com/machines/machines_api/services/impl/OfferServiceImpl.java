package com.machines.machines_api.services.impl;

import com.machines.machines_api.enums.Role;
import com.machines.machines_api.exceptions.common.AccessDeniedException;
import com.machines.machines_api.exceptions.offer.OfferInvalidMainPicException;
import com.machines.machines_api.exceptions.offer.OfferNotFoundException;
import com.machines.machines_api.models.dto.auth.PublicUserDTO;
import com.machines.machines_api.models.dto.request.OfferRequestDTO;
import com.machines.machines_api.models.dto.response.OfferResponseDTO;
import com.machines.machines_api.models.entity.*;
import com.machines.machines_api.repositories.OfferRepository;
import com.machines.machines_api.services.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {
    private final UserService userService;
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
    public OfferResponseDTO create(OfferRequestDTO offerRequestDTO, PublicUserDTO user) {
        boolean isValidMainPicture = offerRequestDTO.getPictureIds().contains(offerRequestDTO.getMainPictureId());
        if (!isValidMainPicture) {
            throw new OfferInvalidMainPicException();
        }

        User owner = userService.findById(user.getId());

        Offer offer = modelMapper.map(offerRequestDTO, Offer.class);
        mapRequestDTOIdsToEntities(offerRequestDTO, offer);
        offer.setOwner(owner);

        Offer savedOffer = offerRepository.save(offer);
        return modelMapper.map(savedOffer, OfferResponseDTO.class);
    }

    @Override
    public OfferResponseDTO update(UUID id, OfferRequestDTO offerRequestDTO, PublicUserDTO user) {
        Offer offer = getEntityById(id);

        if (!user.getRole().equals(Role.ADMIN)) {
            if (!offer.getOwner().getId().equals(user.getId())) {
                throw new AccessDeniedException();
            }
        }

        modelMapper.map(offerRequestDTO, offer);
        mapRequestDTOIdsToEntities(offerRequestDTO, offer);

        Offer updatedOffer = offerRepository.save(offer);
        return modelMapper.map(updatedOffer, OfferResponseDTO.class);
    }

    @Override
    public void delete(UUID id, PublicUserDTO user) {
        Offer offer = getEntityById(id);

        if (!user.getRole().equals(Role.ADMIN)) {
            if (!offer.getOwner().getId().equals(user.getId())) {
                throw new AccessDeniedException();
            }
        }

        offer.delete();
        offerRepository.save(offer);
    }

    @Override
    public Offer getEntityById(UUID id) {
        Optional<Offer> offer = offerRepository.findByIdAndDeletedAtIsNull(id);

        if (offer.isEmpty()) {
            throw new OfferNotFoundException();
        }

        return offer.get();
    }

    public void mapRequestDTOIdsToEntities(OfferRequestDTO offerRequestDTO, Offer offer) {
        if (offerRequestDTO.getSubcategoryId() != null) {
            Subcategory subcategory = subcategoryService.getSubCategoryEntityById(offerRequestDTO.getSubcategoryId());
            offer.setSubcategory(subcategory);
        }

        if (offerRequestDTO.getCityId() != null) {
            City city = cityService.getEntityById(offerRequestDTO.getCityId());
            offer.setCity(city);
        }

        if (offerRequestDTO.getMainPictureId() != null) {
            File mainPicture = fileService.getEntityById(offerRequestDTO.getMainPictureId());
            offer.setMainPicture(mainPicture);
        }

        if (offerRequestDTO.getPictureIds() != null) {
            Set<File> pictures = offerRequestDTO.getPictureIds().stream().map(fileService::getEntityById).collect(Collectors.toSet());
            offer.setPictures(pictures);
        }
    }
}
