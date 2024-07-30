package com.machines.machines_api.exceptions.user;

import com.machines.machines_api.exceptions.common.NoSuchElementException;

/**
 * Exception indicating that the user is not found.
 * Sets the appropriate message using MessageSource (the messages are in src/main/resources/messages).
 */
public class UserNotFoundException extends NoSuchElementException {
    public UserNotFoundException() {
        super("Потребителят не е намерен!");
    }
}