package ru.lihogub.softwaredesigntechnologies.exception;

public class RoleNotFoundException extends Exception {
    public RoleNotFoundException() {
    }

    public RoleNotFoundException(String message) {
        super(message);
    }
}
