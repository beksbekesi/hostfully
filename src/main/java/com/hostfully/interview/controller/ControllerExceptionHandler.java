package com.hostfully.interview.controller;

import com.hostfully.interview.domain.dto.exception.InvalidBookingException;
import com.hostfully.interview.domain.dto.exception.InvalidDataException;
import com.hostfully.interview.domain.dto.exception.InvalidDatesException;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(BindException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleValidationException(BindException ex) {
    return ResponseEntity.badRequest()
        .body(
            ex.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(" and ")));
  }

  @ExceptionHandler(InvalidDataException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<String> handleNotFoundException(InvalidDataException ex) {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(InvalidBookingException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleInvalidBookingException(InvalidBookingException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ExceptionHandler(InvalidDatesException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<String> handleInvalidDatesException(InvalidDatesException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }
}
