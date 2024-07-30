package com.machines.machines_api.exceptions.category;

import com.machines.machines_api.exceptions.common.BadRequestException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class CategoryCreateException extends BadRequestException {
    public CategoryCreateException(boolean isUnique) {
        super(
                isUnique
                        ? "Категория със същото име вече съществува!"
                        : "Невалидни данни за категория!"
        );
    }
}