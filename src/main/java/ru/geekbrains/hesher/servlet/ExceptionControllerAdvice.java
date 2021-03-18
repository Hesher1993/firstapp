package ru.geekbrains.hesher.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<?> handleRuntimeExceptions(InvalidPageException e) {
        log.error(e.getMessage());
        ProductErrorResponse err = new ProductErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
    }
}
