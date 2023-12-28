package com.hostfully.interview.service;

import com.hostfully.interview.domain.dto.BookingDto;
import com.hostfully.interview.domain.dto.BookingStatus;
import com.hostfully.interview.domain.dto.exception.InvalidDataException;
import com.hostfully.interview.mapper.BookingMapper;
import com.hostfully.interview.repository.BookingRepository;
import com.hostfully.interview.repository.PropertyRepository;
import com.hostfully.interview.repository.entity.Booking;
import com.hostfully.interview.repository.entity.GuestDetails;
import com.hostfully.interview.repository.entity.Property;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookingService {

  private final BookingRepository bookingRepository;
  private final PropertyRepository propertyRepository;
  private final BookingMapper bookingMapper;

  public BookingDto createBooking(BookingDto bookingDto) {
    Property property =
        propertyRepository
            .findById(bookingDto.propertyId())
            .orElseThrow(
                () ->
                    new InvalidDataException(
                        "Property with ID not found: " + bookingDto.propertyId()));

    Booking entity = bookingMapper.toEntity(bookingDto, property);
    Booking savedBooking = bookingRepository.save(entity);
    log.info("Booking saved: {} ", savedBooking);
    return bookingMapper.toDto(savedBooking);
  }

  public BookingDto updateBooking(BookingDto bookingDto) {
    if (Objects.isNull(bookingDto.id())) {
      throw new RuntimeException("Booking id cannot be null.");
    }
    Booking existingBooking =
        bookingRepository
            .findById(bookingDto.id())
            .orElseThrow(
                () ->
                    new InvalidDataException(
                        "Booking with ID not found: " + bookingDto.propertyId()));
    existingBooking.setStartDate(bookingDto.startDate());
    existingBooking.setEndDate(bookingDto.endDate());
    List<GuestDetails> newGuestDetails =
        bookingDto.guestDetails().stream()
            .map(bookingMapper::toEntity)
            .peek(guestDetails -> guestDetails.setBooking(existingBooking))
            .toList();
    existingBooking.getGuestDetails().clear();
    existingBooking.getGuestDetails().addAll(newGuestDetails);

    Booking savedUpdatedEntity = bookingRepository.save(existingBooking);
    log.info("Booking updated: {} ", savedUpdatedEntity);
    return bookingMapper.toDto(savedUpdatedEntity);
  }

  public void deleteBooking(String id) {
    if (Objects.isNull(id)) {
      throw new RuntimeException("Booking id cannot be null.");
    }
    Booking existingBooking =
        bookingRepository
            .findById(id)
            .orElseThrow(() -> new InvalidDataException("Booking with ID not found: " + id));

    existingBooking.setBookingStatus(BookingStatus.DELETED);
  }
}
