package com.gardikiotis.WebQuizEngine;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.UnexpectedTypeException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class QuizControllerExceptionsHandler {

    private static final String NOT_FOUND_MESSAGE = "Quiz not found";

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNotFound(Exception e) {
        return ResponseEntity.notFound().build();
    }

/*    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity handleValidationExceptions(
            UnexpectedTypeException ex) {

        return ResponseEntity.badRequest().body(new Error("400","Missing field"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        return ResponseEntity.badRequest().body(new Error("400","Invalid Input"));
    }*/
}
