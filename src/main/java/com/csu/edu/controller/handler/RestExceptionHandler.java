package com.csu.edu.controller.handler;

import com.csu.edu.dto.ExceptionMessage;
import com.csu.edu.exception.DataNotFoundException;
import com.csu.edu.exception.WrongRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ExceptionMessage> handleDataNotFoundException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.unprocessableEntity().body(new ExceptionMessage(e.getMessage()));
    }

    @ExceptionHandler(WrongRequestException.class)
    public ResponseEntity<ExceptionMessage> handleWrongRequestException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(new ExceptionMessage(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionMessage> handleException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(new ExceptionMessage(e.getMessage()));
    }
}
