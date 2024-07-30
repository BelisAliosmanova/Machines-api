package com.machines.machines_api.annotations;

import com.machines.machines_api.validators.OptionalDecimalMinValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Constraint(validatedBy = OptionalDecimalMinValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface OptionalDecimalMin {
    String message() default "Value is below the minimum limit";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    double value(); // Minimum value
}