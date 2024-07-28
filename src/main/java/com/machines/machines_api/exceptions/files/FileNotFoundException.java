package com.machines.machines_api.exceptions.files;

import com.machines.machines_api.exceptions.common.NoSuchElementException;

/**
 * Exception thrown when a file is not found.
 * Sets the appropriate message using MessageSource (the messages are in src/main/resources/messages).
 */
public class FileNotFoundException extends NoSuchElementException {
    public FileNotFoundException() {
        super("Файлът не е намерен!");
    }
}
