package com.machines.machines_api.exceptions.offer;

import com.machines.machines_api.exceptions.common.BadRequestException;

public class OfferInvalidMainPicException extends BadRequestException {
    public OfferInvalidMainPicException() {
        super("Главната снимка трябва да е в списъка със снимките на обявата!");
    }
}
