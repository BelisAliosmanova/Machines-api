package com.machines.machines_api.handlers;

import com.machines.machines_api.exceptions.common.AccessDeniedException;
import com.machines.machines_api.exceptions.common.ApiException;
import com.machines.machines_api.exceptions.common.InternalServerErrorException;
import com.machines.machines_api.exceptions.common.ValidationException;
import com.machines.machines_api.exceptions.user.UserLoginException;
import com.machines.machines_api.models.dto.response.ExceptionResponse;
import com.machines.machines_api.utils.ApiExceptionParser;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.transaction.TransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

/**
 * Global exception handler for handling various types of exceptions and converting them into standardized API responses.
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleRuntimeExceptions(RuntimeException exception) {
        // Log data
        exception.printStackTrace();
        return handleApiExceptions(new InternalServerErrorException(Objects.requireNonNull(getMessageSource())));
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<ExceptionResponse> handleInternalAuthServiceExceptions(InternalAuthenticationServiceException exception) {
        Throwable cause = exception.getCause();

        if (cause instanceof ApiException) {
            return handleApiExceptions((ApiException) cause);
        }

        return handleRuntimeExceptions(exception);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleBadCredentialsExceptions() {
        return handleApiExceptions(new UserLoginException(Objects.requireNonNull(getMessageSource())));
    }

    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleAccessDeniedExceptions() {
        return handleApiExceptions(new AccessDeniedException(Objects.requireNonNull(getMessageSource())));
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ExceptionResponse> handleApiExceptions(ApiException exception) {
        ExceptionResponse apiException = ApiExceptionParser.parseException(exception);

        return ResponseEntity
                .status(apiException.getStatus())
                .body(apiException);
    }

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<ExceptionResponse> handleTransactionExceptions(TransactionException exception) {
        if (exception.getRootCause() instanceof ConstraintViolationException) {
            return handleConstraintValidationExceptions((ConstraintViolationException) exception.getRootCause());
        }

        return handleRuntimeExceptions(exception);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handleConstraintValidationExceptions(ConstraintViolationException exception) {
        return handleApiExceptions(new ValidationException(exception.getConstraintViolations()));
    }
}
