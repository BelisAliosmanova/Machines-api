package com.machines.machines_api.exceptions.location.regions;

import com.machines.machines_api.exceptions.common.NoSuchElementException;

public class RegionNotFoundException extends NoSuchElementException {
    public RegionNotFoundException() {
        super("Областта не е намерена!");
    }
}
