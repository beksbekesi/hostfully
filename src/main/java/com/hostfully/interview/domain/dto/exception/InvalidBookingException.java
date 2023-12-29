package com.hostfully.interview.domain.dto.exception;

public class InvalidBookingException extends RuntimeException {
  public InvalidBookingException(String message) {
    super(message);
  }
}
