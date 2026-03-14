package com.zunino.sport.persistence.exception;

public class TooYoungException extends RuntimeException {
    public TooYoungException(String message) {
        super(message);
    }
}