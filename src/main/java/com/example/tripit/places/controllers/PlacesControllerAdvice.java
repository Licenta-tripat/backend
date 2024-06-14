package com.example.tripit.places.controllers;

import com.example.tripit.exceptions.CategoryTomTomException;
import com.example.tripit.exceptions.NearbyTomTomException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PlacesControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CategoryTomTomException.class, NearbyTomTomException.class})
    public ResponseEntity<?> handleTomTomException(Exception e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
