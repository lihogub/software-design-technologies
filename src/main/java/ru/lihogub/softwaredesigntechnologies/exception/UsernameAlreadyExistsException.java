package ru.lihogub.softwaredesigntechnologies.exception;

public class UsernameAlreadyExistsException extends Exception {
    public UsernameAlreadyExistsException() {
    }

    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
