package com.example.projectbankend.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> notFoundException(NotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", 404);
        response.put("status", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(SystemErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> internalException(SystemErrorException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", 500);
        response.put("message", "Xảy ra lỗi trong quá trình thực hiện");
        return ResponseEntity.status(500).body(response);
    }

    @ExceptionHandler(WrongPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> wrongPasswordException(WrongPasswordException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", 400);
        response.put("message", ex.getMessage());
        return ResponseEntity.status(400).body(response);
    }
}
