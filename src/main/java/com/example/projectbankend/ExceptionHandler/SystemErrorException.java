package com.example.projectbankend.ExceptionHandler;

public class SystemErrorException extends RuntimeException {
    public SystemErrorException(String message) {
        super(message);
    }
}
