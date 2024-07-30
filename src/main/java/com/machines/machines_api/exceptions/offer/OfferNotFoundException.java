package com.machines.machines_api.exceptions.offer;

import com.machines.machines_api.exceptions.common.NoSuchElementException;

public class OfferNotFoundException extends NoSuchElementException {
    public OfferNotFoundException() {
        super("Обявата не е намерена!");
    }
}
