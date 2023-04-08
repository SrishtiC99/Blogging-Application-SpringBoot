package com.srishti.blogapis.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.srishti.blogapis.payloads.APIResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException excep) {
        String message = excep.getMessage();
        APIResponse response = new APIResponse(message, false);

        return new ResponseEntity<APIResponse>(response, HttpStatus.NOT_FOUND);
    }
    
}
