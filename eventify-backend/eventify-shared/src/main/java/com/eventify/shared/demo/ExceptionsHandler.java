package com.eventify.shared.demo;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

/**
 * Created by spasoje on 09-Feb-19.
 */
@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    protected ResponseEntity<Map<String, Object>> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("errors", singletonList(ex.getMessage()));
        //todo we are exposing part of the exception like this, we should remap it to error message somehow
        return new ResponseEntity<>(body,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());

        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
