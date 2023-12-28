package com.hostfully.interview.controller;

import com.hostfully.interview.domain.dto.BookingDto;
import com.hostfully.interview.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/bookings")
public class BookingController {

  private final BookingService bookingService;

  @PostMapping
  public ResponseEntity<BookingDto> createBooking(@RequestBody @Valid BookingDto bookingDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.createBooking(bookingDto));
  }
}
