package com.machines.machines_api.validators;

import com.machines.machines_api.annotations.AnnotationFormatException;
import com.machines.machines_api.annotations.AnnotationInvocationHandler;
import com.machines.machines_api.annotations.Conditional;
import io.leangen.geantyref.TypeFactory;
import jakarta.validation.*;
import jakarta.validation.metadata.BeanDescriptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

public class ConditionalValidator implements ConstraintValidator<Conditional, Object> {
    private Class<? extends Annotation> validatorAnnotationClass;
    private Map<String, Object> params = new HashMap<>();
    private Class<?>[] groups;

    @Override
    public void initialize(Conditional constraintAnnotation) {
        this.validatorAnnotationClass = constraintAnnotation.useAnnotation();
        this.groups = constraintAnnotation.groups();
        String[] paramArray = constraintAnnotation.params();
        for (String param : paramArray) {
            String[] keyValue = param.split("=", 2);
            if (keyValue.length == 2) {
                params.put(keyValue[0], convertToCorrectType(keyValue[0], keyValue[1]));
            }
        }
        System.out.println("Initialization complete. Validator: " + validatorAnnotationClass.getName());
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }


        try {
            // Create an instance of the underlying validator annotation with the specified parameters
            System.out.println("Creating validator annotation...");
            Annotation validatorAnnotation = createValidatorAnnotation();

            DummyClass dummyClass = new DummyClass(value);


            System.out.println("Validator annotation created: " + validatorAnnotation);

            var idk = validatorAnnotation.getClass().getAnnotations();
            var idk2 = validatorAnnotation.getClass().getDeclaredAnnotations();

            // Get a Validator instance
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

            var type = validatorAnnotation.annotationType();
            // Validate the value with the created validator annotation
            Set<ConstraintViolation<Object>> validate = validator.validate(value);


            var validate1 = validator.validate(value, validatorAnnotation.annotationType());


            var violations = validator.validateProperty(
                    dummyClass,
                    "value",
                    validatorAnnotation.annotationType()
            );

            System.out.println();
            if (violations.isEmpty()) {
                return false;
            } else {
                context.disableDefaultConstraintViolation();
                for (var violation : violations) {
                    context.buildConstraintViolationWithTemplate(violation.getMessage())
                            .addConstraintViolation();
                }
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Annotation createValidatorAnnotation() throws AnnotationFormatException {
        System.out.println("Creating proxy for " + validatorAnnotationClass.getName());

        return (Annotation) Proxy.newProxyInstance(
                validatorAnnotationClass.getClassLoader(),
                new Class[]{validatorAnnotationClass},
                new AnnotationInvocationHandler(validatorAnnotationClass, params == null ? Collections.emptyMap() : params)
        );
    }

    private Object convertToCorrectType(String key, String value) {
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
            } else if (returnType.equals(boolean.class) || returnType.equals(Boolean.class)) {
                return Boolean.parseBoolean(value);
            }
            return value; // default to String
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Invalid parameter name: " + key, e);
        }
    }

    // Dummy class to use for validation context
    private static class DummyClass {
        @SuppressWarnings("unused")
        public Object value;

        public DummyClass(Object value) {
            this.value = value;
        }
    }
}
