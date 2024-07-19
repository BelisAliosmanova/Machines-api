package com.machines.machines_api.exceptions.location.countries;

import com.machines.machines_api.exceptions.common.BadRequestException;

public class CountryCreateException extends BadRequestException {
    public CountryCreateException(boolean isUnique) {
        super(
                isUnique
                        ? "Държава със същото име вече съществува!"
                        : "Невалидни данни за държавата!"
        );
    }
}
