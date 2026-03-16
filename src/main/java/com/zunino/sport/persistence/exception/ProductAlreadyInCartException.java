package com.zunino.sport.persistence.exception;

public class ProductAlreadyInCartException extends RuntimeException {
    public ProductAlreadyInCartException(String message) {
        super(message);
    }
}
