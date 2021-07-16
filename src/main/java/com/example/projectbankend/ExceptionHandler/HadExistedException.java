package com.example.projectbankend.ExceptionHandler;

public class HadExistedException extends RuntimeException{
    public HadExistedException(String message) {
        super(message);
    }
}
