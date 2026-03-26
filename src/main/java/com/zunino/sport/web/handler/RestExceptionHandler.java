package com.zunino.sport.web.handler;

import com.zunino.sport.persistence.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;


@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(TooYoungException.class)
    public ResponseEntity<Error> handleException(TooYoungException tooYoungException) {
        Error errorResponse = new Error("not-over-18", tooYoungException.getMessage());
        return new ResponseEntity<Error>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Error> handleException(UserNotFoundException userNotFoundException) {
        Error errorResponse = new Error("user-not-found", userNotFoundException.getMessage());
        return new ResponseEntity<Error>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Error> handleException(ProductNotFoundException productNotFound) {
        Error errorResponse = new Error("product-not-found", productNotFound.getMessage());
        return new ResponseEntity<Error>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotEnoughProductException.class)
    public ResponseEntity<Error> handleException(NotEnoughProductException notEnoughProductException) {
        Error errorResponse = new Error("invalid-quantity", notEnoughProductException.getMessage());
        return new ResponseEntity<Error>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CartEmptyException.class)
    public ResponseEntity<Error> handleException(CartEmptyException cartEmptyException) {
        Error errorResponse = new Error("empty-cart", cartEmptyException.getMessage());
        return new ResponseEntity<Error>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProductAlreadyInCartException.class)
    public ResponseEntity<Error> handleException(ProductAlreadyInCartException productAlreadyInCartException) {
        Error errorResponse = new Error("product-already-in-cart", productAlreadyInCartException.getMessage());
        return new ResponseEntity<Error>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<Error> handleException(UserAlreadyExists userAlreadyExists) {
        Error errorResponse = new Error("user-already-exists", userAlreadyExists.getMessage());
        return new ResponseEntity<Error>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Error> handleException(UnauthorizedException unauthorizedException) {
        Error errorResponse = new Error("user-not-identified", unauthorizedException.getMessage());
        return new ResponseEntity<Error>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Error> handleException(ForbiddenException forbiddenException) {
        Error errorResponse = new Error("user-not-allowed", forbiddenException.getMessage());
        return new ResponseEntity<Error>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Error>> handleException(MethodArgumentNotValidException ex) {
        List<Error> errorList = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            errorList.add(new Error(error.getField(), error.getDefaultMessage()));
        });

        return ResponseEntity.badRequest().body(errorList);
    }
}
