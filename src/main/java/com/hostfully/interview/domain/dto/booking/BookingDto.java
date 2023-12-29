package com.hostfully.interview.domain.dto.booking;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record BookingDto(
    String id,
    @NotNull(message = "Booking startDate cannot be null") LocalDate startDate,
    @NotNull(message = "Booking endDate cannot be null") LocalDate endDate,
    BookingStatus bookingStatus,
    @NotNull(message = "Booking guestDetails cannot be null")
    @NotEmpty(message = "Booking guestDetails cannot be empty")
    List<GuestDetailsDto> guestDetails,
    @NotNull(message = "Booking propertyId cannot be null") String propertyId) {}
