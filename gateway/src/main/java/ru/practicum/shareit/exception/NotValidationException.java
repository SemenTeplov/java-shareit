package ru.practicum.shareit.exception;

public class NotValidationException extends RuntimeException {
    public NotValidationException(String message) {
        super(message);
    }
}
