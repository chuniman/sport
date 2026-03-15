package com.zunino.sport.persistence.exception;

public class NotEnoughProductException extends RuntimeException {
    public NotEnoughProductException(String message) {
        super(message);
    }
}
