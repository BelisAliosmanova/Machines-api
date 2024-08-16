package com.machines.machines_api.services.impl;

import com.machines.machines_api.exceptions.advertisement.AdvertisementNotFoundException;
import com.machines.machines_api.models.entity.Advertisement;
import com.machines.machines_api.repositories.AdvertisementRepository;
import com.machines.machines_api.services.AdvertisementService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {
    private final AdvertisementRepository advertisementRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Advertisement> getAll() {
        List<Advertisement> categories = advertisementRepository.findAllByDeletedAtIsNull();
        return categories.stream().map(x -> modelMapper.map(x, Advertisement.class)).toList();
    }

    @Override
    public List<Advertisement> getAllAdmin() {
        List<Advertisement> categories = advertisementRepository.findAll();
        return categories.stream().map(x -> modelMapper.map(x, Advertisement.class)).toList();
    }

    @Override
    public Advertisement getAdvertisementById(UUID id) {
        Advertisement advertisement = getAdvertisementEntityById(id);
        return modelMapper.map(advertisement, Advertisement.class);
    }

    @Override
    public Advertisement getAdvertisementByIdAdmin(UUID id) {
        Advertisement advertisement = getAdvertisementEntityByIdAdmin(id);
        return modelMapper.map(advertisement, Advertisement.class);
    }

    @Override
    public Advertisement create(Advertisement advertisement) {
        return advertisementRepository.save(advertisement);
    }

    @Override
    public Advertisement update(UUID id, Advertisement advertisement) {
        advertisementRepository.findById(id).orElseThrow(AdvertisementNotFoundException::new);
        advertisement.setId(id);
        return advertisementRepository.save(advertisement);
    }

    @Override
    public void delete(UUID id) {
        Advertisement existingAdvertisement = getAdvertisementEntityByIdAdmin(id);

        if (existingAdvertisement.getDeletedAt() == null) {
            existingAdvertisement.setDeletedAt(LocalDateTime.now());
            existingAdvertisement.setActive(false);
        } else {
            existingAdvertisement.setActive(true);
            existingAdvertisement.setDeletedAt(null);
        }

        advertisementRepository.save(existingAdvertisement);
    }

    public Advertisement getAdvertisementEntityById(UUID id) {
        Optional<Advertisement> advertisement = advertisementRepository.findByIdAndDeletedAtIsNull(id);

        if (advertisement.isEmpty()) {
            throw new AdvertisementNotFoundException();
        }

        return advertisement.get();
    }

    public Advertisement getAdvertisementEntityByIdAdmin(UUID id) {
        Optional<Advertisement> advertisement = advertisementRepository.findById(id);

        if (advertisement.isEmpty()) {
            throw new AdvertisementNotFoundException();
        }

        return advertisement.get();
    }
}

