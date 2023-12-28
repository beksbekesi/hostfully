package com.hostfully.interview.controller;

import com.hostfully.interview.domain.dto.BookingDto;
import com.hostfully.interview.service.BookingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/bookings")
public class BookingController {

  private final BookingService bookingService;

  @GetMapping("/{bookingId}")
  public ResponseEntity<BookingDto> getBooking(@PathVariable @NotNull String bookingId) {
    return ResponseEntity.status(HttpStatus.OK).body(bookingService.getBooking(bookingId));
  }

  @PostMapping
  public ResponseEntity<BookingDto> createBooking(@RequestBody @Valid BookingDto bookingDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.createBooking(bookingDto));
  }

  @PutMapping
  public ResponseEntity<BookingDto> updateBooking(@RequestBody @Valid BookingDto bookingDto) {
    return ResponseEntity.status(HttpStatus.OK).body(bookingService.updateBooking(bookingDto));
  }

  @DeleteMapping("/{bookingId}")
  public ResponseEntity<Void> deleteBooking(@PathVariable String bookingId) {
    bookingService.deleteBooking(bookingId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
