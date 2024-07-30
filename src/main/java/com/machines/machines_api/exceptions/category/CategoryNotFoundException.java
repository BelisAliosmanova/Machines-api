package com.machines.machines_api.exceptions.category;

import com.machines.machines_api.exceptions.common.NoSuchElementException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class CategoryNotFoundException extends NoSuchElementException {
    public CategoryNotFoundException() {
        super("Категорията не е намерена!");
    }
}
