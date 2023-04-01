package com.example.springbootdemo.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<String> ExceptionHandler(MethodArgumentNotValidException e) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("asd","qwe");
        ResponseEntity<String> response = new ResponseEntity<>(e.getFieldError().getDefaultMessage(), httpHeaders, HttpStatus.OK);

        return response;
    }
}
