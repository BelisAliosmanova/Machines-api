package com.machines.machines_api.validators;

import com.machines.machines_api.annotations.OptionalMin;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OptionalMinValidator implements ConstraintValidator<OptionalMin, Integer> {
    private long min;

    @Override
    public void initialize(OptionalMin constraintAnnotation) {
        min = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }

        return value >= min;
    }
}
