package com.smyunis.halite.application.applicationexceptions;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
