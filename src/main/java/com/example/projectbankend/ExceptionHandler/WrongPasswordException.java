package com.example.projectbankend.ExceptionHandler;

public class WrongPasswordException extends RuntimeException{
    public WrongPasswordException(String message) {
        super(message);
    }
}
