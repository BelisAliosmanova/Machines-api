package com.machines.machines_api.services;

import com.machines.machines_api.models.entity.Advertisement;

import java.util.List;
import java.util.UUID;

public interface AdvertisementService {
    List<Advertisement> getAll();
    Advertisement getAdvertisementById(UUID id);

    Advertisement create(Advertisement advertisement);

    Advertisement update(UUID id, Advertisement advertisement);

    void delete(UUID id);

    Advertisement getAdvertisementEntityById(UUID id);

    List<Advertisement> getAllAdmin();

    Advertisement getAdvertisementByIdAdmin(UUID id);
}
