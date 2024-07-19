package com.machines.machines_api.exceptions.location.cities;

import com.machines.machines_api.exceptions.common.BadRequestException;

public class CityCreateException extends BadRequestException {
    public CityCreateException(boolean isUnique) {
        super(
                isUnique
                        ? "Град със същото име вече съществува!"
                        : "Невалидни данни за града!"
        );
    }
}
