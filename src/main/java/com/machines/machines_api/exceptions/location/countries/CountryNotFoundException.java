package com.machines.machines_api.exceptions.location.countries;

import com.machines.machines_api.exceptions.common.NoSuchElementException;

public class CountryNotFoundException extends NoSuchElementException {
    public CountryNotFoundException() {
        super("Държавата не е намерена!");
    }
}
