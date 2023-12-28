package com.hostfully.interview.service;

import com.hostfully.interview.domain.dto.BookingDto;
import com.hostfully.interview.mapper.BookingMapper;
import com.hostfully.interview.repository.BookingRepository;
import com.hostfully.interview.repository.entity.Booking;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookingService {

  private final BookingRepository bookingRepository;
  private final BookingMapper bookingMapper;

  public BookingDto createBooking(BookingDto bookingDto) {
    Booking savedBooking = bookingRepository.save(bookingMapper.toEntity(bookingDto));
    log.info("Booking saved: {} ", savedBooking);
    return bookingMapper.toDto(savedBooking);
  }

  public BookingDto updateBooking(BookingDto bookingDto) {
    return null;
  }

  public void deleteBooking(BookingDto bookingDto) {}
}
