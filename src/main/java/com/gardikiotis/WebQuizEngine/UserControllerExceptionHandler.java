package com.gardikiotis.WebQuizEngine;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserControllerExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity notFound(){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity illegalArgument(){
        return ResponseEntity.badRequest().build();
    }
}
