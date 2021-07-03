package com.example.projectbankend.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class SystemErrorHandler {

    @ExceptionHandler(SystemErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> excepiton(SystemErrorException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", 500);
        response.put("message", "Xảy ra lỗi trong quá trình thực hiện");
        return ResponseEntity.status(500).body(response);
    }
}
