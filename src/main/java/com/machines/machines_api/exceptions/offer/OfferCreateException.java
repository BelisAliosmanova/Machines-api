package com.machines.machines_api.exceptions.offer;

import com.machines.machines_api.exceptions.common.BadRequestException;

public class OfferCreateException extends BadRequestException {
    public OfferCreateException() {
        super("Невалидни данни за обявата!");
    }
}

