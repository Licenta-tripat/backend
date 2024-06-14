package com.example.tripit.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class NearbyTomTomException extends RuntimeException{
    public NearbyTomTomException(String message) {
        super(message);
    }
}
