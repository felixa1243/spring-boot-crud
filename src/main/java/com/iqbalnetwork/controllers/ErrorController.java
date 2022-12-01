package com.iqbalnetwork.controllers;

import com.iqbalnetwork.controllers.exceptions.EntityExistException;
import com.iqbalnetwork.models.responses.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleDataNotFound(NoSuchElementException err) {
        return ResponseEntity.status(404).body(new ErrorResponse(404, err.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllException(Exception err) {
        return ResponseEntity.status(500).body(new ErrorResponse(500, err.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException err) {
        List<FieldError> fieldErrors = err.getBindingResult().getFieldErrors();
        List<String> errors = new ArrayList<>();
        for (FieldError error : fieldErrors) {
            errors.add(error.getDefaultMessage());
        }
        return ResponseEntity.status(400).body(new ErrorResponse(400, errors.toString()));
    }

    @ExceptionHandler(EntityExistException.class)
    public ResponseEntity<ErrorResponse> handleExistEntityException(EntityExistException e) {
        return ResponseEntity
                .status(400)
                .body(new ErrorResponse(400, e.getMessage()));
    }
}
