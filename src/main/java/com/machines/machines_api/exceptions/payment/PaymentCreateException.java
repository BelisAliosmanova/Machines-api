package com.machines.machines_api.exceptions.payment;

import com.machines.machines_api.exceptions.common.BadRequestException;

public class PaymentCreateException extends BadRequestException {
    public PaymentCreateException() {
        super("Невалидни данни за платежната сесия!");
    }
}
