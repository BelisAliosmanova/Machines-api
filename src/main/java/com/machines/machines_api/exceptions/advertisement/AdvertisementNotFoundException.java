package com.machines.machines_api.exceptions.advertisement;

import com.machines.machines_api.exceptions.common.NoSuchElementException;

public class AdvertisementNotFoundException extends NoSuchElementException {
    public AdvertisementNotFoundException() {
        super("Рекламата не е намерена!");
    }
}
