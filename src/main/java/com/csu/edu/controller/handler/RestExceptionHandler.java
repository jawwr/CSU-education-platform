package com.csu.edu.controller.handler;

import com.csu.edu.dto.ExceptionMessage;
import com.csu.edu.exception.DataNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ExceptionMessage> handleDataNotFoundException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.unprocessableEntity().body(new ExceptionMessage(e.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ExceptionMessage handleException(Exception e) {
        log.error(e.getMessage());
        return new ExceptionMessage(e.getMessage());
    }
}
