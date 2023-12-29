package com.hostfully.interview.domain.dto.exception;

public class InvalidDatesException extends RuntimeException {
  public InvalidDatesException(String message) {
    super(message);
  }
}
