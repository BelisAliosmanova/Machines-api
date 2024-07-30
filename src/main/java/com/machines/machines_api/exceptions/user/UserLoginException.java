package com.machines.machines_api.exceptions.user;

import com.machines.machines_api.exceptions.common.BadRequestException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * Exception thrown when there is an issue with user login, such as invalid email or password.
 */
public class UserLoginException extends BadRequestException {
    public UserLoginException() {
        super("Невалиден имейл или парола!");
    }
}
