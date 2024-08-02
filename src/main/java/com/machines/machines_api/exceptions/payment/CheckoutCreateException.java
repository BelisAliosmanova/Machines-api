package com.machines.machines_api.exceptions.payment;

import com.machines.machines_api.exceptions.common.BadRequestException;

public class CheckoutCreateException extends BadRequestException {
    public CheckoutCreateException() {
        super("Невалидни данни за платежната сесия!");
    }
}
