package com.machines.machines_api.exceptions.category;

import com.machines.machines_api.exceptions.common.NoSuchElementException;

public class CategoryNotFoundException extends NoSuchElementException {
    public CategoryNotFoundException() {
        super("Категорията не е намерена!");
    }
}
