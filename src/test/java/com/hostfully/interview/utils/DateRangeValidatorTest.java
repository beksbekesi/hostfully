package com.hostfully.interview.utils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.hostfully.interview.domain.dto.exception.InvalidDatesException;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class DateRangeValidatorTest {

  @Test
  void validDateRangesNoExceptionThrown() {
    assertDoesNotThrow(() -> DateRangeValidator.validateDates(LocalDate.now(), LocalDate.now().plusDays(1)));
    assertDoesNotThrow(() -> DateRangeValidator.validateDates(LocalDate.now(), LocalDate.now().plusDays(5)));
  }

  @Test
  void invalidDataRangesInvalidDatesExceptionThrown() {
    assertThrows(
        InvalidDatesException.class,
        () -> DateRangeValidator.validateDates(LocalDate.now(), LocalDate.now().minusDays(5)));
    assertThrows(
        InvalidDatesException.class,
        () -> DateRangeValidator.validateDates(LocalDate.now(), LocalDate.now().minusDays(1)));
  }
}
