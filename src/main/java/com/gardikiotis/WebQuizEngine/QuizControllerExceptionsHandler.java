package com.gardikiotis.WebQuizEngine;

import com.gardikiotis.WebQuizEngine.models.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
    }*/

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        return new ResponseEntity(new Error("400","Password must have at least six characters"), HttpStatus.BAD_REQUEST);
    }
}
