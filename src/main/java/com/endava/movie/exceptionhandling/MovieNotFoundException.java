package com.endava.movie.exceptionhandling;

import org.springframework.http.HttpStatus;

public class MovieNotFoundException extends Exception{
    private final HttpStatus responseCode;

    public MovieNotFoundException(String message, HttpStatus code) {
        super(message);
        this.responseCode = code;
    }

    public HttpStatus getResponseCode() {
        return responseCode;
    }
}
