package com.moviebooking.bookingservice.exception;

public class MovieServiceUnavailableException extends RuntimeException {
    public MovieServiceUnavailableException(String message) {
        super(message);
    }
}
