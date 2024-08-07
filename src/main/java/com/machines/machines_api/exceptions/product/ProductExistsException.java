package com.machines.machines_api.exceptions.product;

import com.machines.machines_api.exceptions.common.BadRequestException;

public class ProductExistsException extends BadRequestException {
    public ProductExistsException(String fieldName) {
        super("Продукт със същото " + fieldName + " вече същестува!");
    }
}
