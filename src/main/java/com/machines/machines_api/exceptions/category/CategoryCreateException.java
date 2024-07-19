package com.machines.machines_api.exceptions.category;

import com.machines.machines_api.exceptions.common.BadRequestException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class CategoryCreateException extends BadRequestException {
    public CategoryCreateException(MessageSource messageSource, boolean isUnique) {
        super(
                isUnique
                        ? messageSource.getMessage("category.create.exists", null, LocaleContextHolder.getLocale())
                        : messageSource.getMessage("category.create.invalid", null, LocaleContextHolder.getLocale())
        );
    }
}