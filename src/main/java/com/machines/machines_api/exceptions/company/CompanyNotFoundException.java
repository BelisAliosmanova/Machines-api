package com.machines.machines_api.exceptions.company;

import com.machines.machines_api.exceptions.common.NoSuchElementException;

public class CompanyNotFoundException extends NoSuchElementException {
    public CompanyNotFoundException() {
        super("Фирмата не е намерена!");
    }
}
