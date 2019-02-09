package com.eventify.shared.demo;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by spasoje on 09-Feb-19.
 */
@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    protected ResponseEntity<DataIntegrityViolationException> handleConflict(DataIntegrityViolationException ex, WebRequest request) {//TODO Request can be deleted?
        return new ResponseEntity<DataIntegrityViolationException>(ex,HttpStatus.CONFLICT);
    }
}
