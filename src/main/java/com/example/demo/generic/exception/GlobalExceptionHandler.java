package com.example.demo.generic.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice   //It handles the exception globally in the application
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
/*
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<CommonResponse> handleDataNotFoundException(DataNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CommonResponse(true, ex.getMessage(),null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse> handleValidException(MethodArgumentNotValidException m){
        Map<String,String> errors=new HashMap<>();
        m.getBindingResult().getFieldErrors().forEach(error->errors.put(error.getField(),error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse(true,"validation failed", errors));
    }*/

    //Requestparam & pathvariable validation
/*    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CommonResponse> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(v ->
                errors.put(v.getPropertyPath().toString(), v.getMessage()));
        return ResponseEntity.badRequest().body(new CommonResponse(true, "Validation Failed", errors));
    }*/
}
