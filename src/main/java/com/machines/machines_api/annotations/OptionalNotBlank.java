package com.machines.machines_api.annotations;

import com.machines.machines_api.validators.OptionalNotBlankValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Constraint(validatedBy = OptionalNotBlankValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface OptionalNotBlank {
    String message() default "Value must not be blank";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
