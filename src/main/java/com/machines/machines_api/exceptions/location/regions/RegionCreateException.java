package com.machines.machines_api.exceptions.location.regions;

import com.machines.machines_api.exceptions.common.BadRequestException;

public class RegionCreateException extends BadRequestException {
    public RegionCreateException(boolean isUnique) {
        super(
                isUnique
                        ? "Област със същото име вече съществува!"
                        : "Невалидни данни за областта!"
        );
    }
}
