package com.hostfully.interview.domain.dto.booking;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GuestDetailsDto(
    String id,
    @NotBlank(message = "Guest name cannot be null")
    String name,
    @NotNull(message = "Guest age cannot be null")
    @Min(value = 0, message = "Age must be a positive number")
    Integer age) {}
