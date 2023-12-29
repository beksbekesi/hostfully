package com.hostfully.interview.domain.dto.block;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record BlockDto(
    String id,
    @NotNull(message = "Booking startDate cannot be null")
    LocalDate startDate,
    @NotNull(message = "Booking endDate cannot be null")
    LocalDate endDate,
    String reason,
    @NotNull(message = "Booking propertyId cannot be null")
    String propertyId) {}
