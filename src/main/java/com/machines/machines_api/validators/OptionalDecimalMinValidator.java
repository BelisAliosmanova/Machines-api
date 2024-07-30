package com.machines.machines_api.validators;

import com.machines.machines_api.annotations.OptionalDecimalMin;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OptionalDecimalMinValidator implements ConstraintValidator<OptionalDecimalMin, Double> {
    private double min;

    @Override
    public void initialize(OptionalDecimalMin constraintAnnotation) {
        this.min = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return value >= min;
    }
}
