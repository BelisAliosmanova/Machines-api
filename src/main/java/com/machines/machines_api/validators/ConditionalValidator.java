package com.machines.machines_api.validators;

import com.machines.machines_api.annotations.Conditional;
import jakarta.validation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ConditionalValidator implements ConstraintValidator<Conditional, Object> {
    private Class<? extends Annotation> validatorAnnotationClass;
    private final Map<String, Object> params = new HashMap<>();

    @Override
    public void initialize(Conditional constraintAnnotation) {
        this.validatorAnnotationClass = constraintAnnotation.validatedBy();

        String[] paramArray = constraintAnnotation.params();
        for (String param : paramArray) {
            String[] keyValue = param.split("=", 2);

            if (keyValue.length == 2) {
                Object convertedParam = convertToCorrectType(keyValue[0], keyValue[1]);
                params.put(keyValue[0], convertedParam);
            }
        }
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        try {
            // Create an instance of the underlying validator annotation with the specified parameters
            Annotation validatorAnnotation = createValidatorAnnotation();

            // Get a Validator instance
            Validator validator;
            try (var factory = Validation.buildDefaultValidatorFactory()) {
                validator = factory.getValidator();
            }

            // Validate the value with the created validator annotation
            Set<ConstraintViolation<Object>> violations = validator.validateValue(
                    getDummyClass(), "value", value, validatorAnnotation.annotationType());

            if (violations.isEmpty()) {
                return true;
            }

            context.disableDefaultConstraintViolation();

            for (ConstraintViolation<Object> violation : violations) {
                context.buildConstraintViolationWithTemplate(violation.getMessage())
                        .addConstraintViolation();
            }

            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Annotation createValidatorAnnotation() {
        // Use dynamic proxy to create an instance of the annotation with the specified parameters
        return (Annotation) java.lang.reflect.Proxy.newProxyInstance(
                validatorAnnotationClass.getClassLoader(),
                new Class[]{validatorAnnotationClass},
                (proxy, method, args) -> params.getOrDefault(method.getName(), method.getDefaultValue()));
    }

    private Object convertToCorrectType(String key, String value) {
        // Handle conversion logic based on the expected parameter types
        // This is a simplified solution, more comprehensive type handling might be needed in the future
        try {
            Method method = validatorAnnotationClass.getMethod(key);
            Class<?> returnType = method.getReturnType();

            if (returnType.equals(int.class) || returnType.equals(Integer.class)) {
                return Integer.parseInt(value);
            } else if (returnType.equals(long.class) || returnType.equals(Long.class)) {
                return Long.parseLong(value);
            } else if (returnType.equals(double.class) || returnType.equals(Double.class)) {
                return Double.parseDouble(value);
            } else if (returnType.equals(float.class) || returnType.equals(Float.class)) {
                return Float.parseFloat(value);
            }

            return value; // default to String
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Invalid parameter name: " + key, e);
        }
    }

    private Class<Object> getDummyClass() {
        // Returns a dummy class to validate the value
        return Object.class;
    }
}
