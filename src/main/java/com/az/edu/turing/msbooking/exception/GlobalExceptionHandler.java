package com.az.edu.turing.msbooking.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;

import static com.az.edu.turing.msbooking.model.constants.ErrorCode.USER_ALREADY_EXISTS;
import static com.az.edu.turing.msbooking.model.constants.ErrorCode.USER_NOT_FOUND;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<GlobalErrorResponse> alreadyExistsExceptionHandler(AlreadyExistsException exception) {
        exceptionLog(exception);
        return ResponseEntity.status(BAD_REQUEST).body(GlobalErrorResponse.builder()
                .requestId(UUID.randomUUID())
                .errorCode(USER_ALREADY_EXISTS)
                .errorMessage(exception.getMessage())
                .localDateTime(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<GlobalErrorResponse> notFoundExceptionHandler(NotFoundException exception) {
        exceptionLog(exception);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GlobalErrorResponse.builder()
                .requestId(UUID.randomUUID())
                .errorCode(USER_NOT_FOUND)
                .errorMessage(exception.getMessage())
                .localDateTime(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> notValidException(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));

        Map<String, Object> body = new HashMap<>();
        body.put("errors", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    public void exceptionLog(Exception exception) {
        log.info("{} happened {}", exception.getClass().getSimpleName(), exception.getMessage());
    }
}
