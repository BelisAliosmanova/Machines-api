package com.machines.machines_api.exceptions.location.cities;

import com.machines.machines_api.exceptions.common.NoSuchElementException;

public class CityNotFoundException extends NoSuchElementException {
    public CityNotFoundException() {
        super("Градът не е намерен!");
    }
}
