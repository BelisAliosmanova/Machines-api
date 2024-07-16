package com.machines.machines_api.exceptions.user;

import com.machines.machines_api.exceptions.common.NoSuchElementException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * Exception indicating that the user is not found.
 * Sets the appropriate message using MessageSource (the messages are in src/main/resources/messages).
 */
public class UserNotFoundException extends NoSuchElementException {
    public UserNotFoundException(MessageSource messageSource) {
        super(messageSource.getMessage("user.not.found", null, LocaleContextHolder.getLocale()));
    }

    public UserNotFoundException(String field, MessageSource messageSource) {
        super(messageSource.getMessage("user.not.found", null, LocaleContextHolder.getLocale()) + field);
    }
}