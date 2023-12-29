package com.hostfully.interview.utils;

import com.hostfully.interview.domain.dto.exception.InvalidDatesException;

import java.time.LocalDate;

public class DateRangeValidator {
    public static void validateDates(LocalDate startDate, LocalDate endDate){
        if(!startDate.isBefore(endDate)){
            throw new InvalidDatesException("StartDate has to be before EndDate");
        }
    }
}
