package com.dh.integrador.martinezMayra.exceptions;

import org.hibernate.jdbc.BatchFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> ttoErrorResourceNotFound(ResourceNotFoundException rnfe){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR: "+rnfe.getMessage());
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> ttoBadRequestException (BadRequestException bdr){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(("ERROR: "+bdr.getMessage()));
    }
}
