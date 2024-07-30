package com.machines.machines_api.annotations;

import com.machines.machines_api.validators.ConditionalValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ConditionalValidator.class })
public @interface Conditional {
    String message() default "Невалидни данни";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    // Specifies the underlying validator annotation
    Class<? extends Annotation> validatedBy();

    // The properties for the underlying validator
    String[] params() default {};
}
