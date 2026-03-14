package com.zunino.sport.web.handler;

import com.zunino.sport.persistence.exception.TooYoungException;
import com.zunino.sport.persistence.exception.UserNotFoundException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Error>> handleException(MethodArgumentNotValidException ex) {
        List<Error> errorList = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            errorList.add(new Error(error.getField(), error.getDefaultMessage()));
        });

        return ResponseEntity.badRequest().body(errorList);
    }
}
