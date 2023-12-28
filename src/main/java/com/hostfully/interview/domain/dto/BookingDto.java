package com.hostfully.interview.domain.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BookingDto(
    String id,
    @NotNull(message = "Booking startDate cannot be null") LocalDate startDate,
    @NotNull(message = "Booking endDate cannot be null") LocalDate endDate,
    BookingStatus bookingStatus,
    @NotNull(message = "Booking guestId cannot be null") String guestId,
    @NotNull(message = "Booking propertyId cannot be null") String propertyId) {}
