package com.hostfully.interview.service;

import com.hostfully.interview.domain.dto.booking.BookingDto;
import com.hostfully.interview.domain.dto.booking.BookingStatus;
import com.hostfully.interview.domain.dto.exception.InvalidBookingException;
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
  private final BlockService blockService;

  public BookingDto getBooking(String id) {
    return bookingRepository
        .findById(id)
        .map(bookingMapper::toDto)
        .orElseThrow(() -> new InvalidDataException("Booking with ID not found: " + id));
  }

  public BookingDto createBooking(BookingDto bookingDto) {
    Property property =
        propertyRepository.findById(bookingDto.propertyId())
                .orElseThrow(() -> new InvalidDataException("Property with ID not found: " + bookingDto.propertyId()));

    Booking entity = bookingMapper.toEntity(bookingDto, property);
    checkAvailability(entity);
    Booking savedBooking = bookingRepository.save(entity);
    log.info("Booking saved: {} ", savedBooking);
    return bookingMapper.toDto(savedBooking);
  }

  public BookingDto updateBooking(BookingDto bookingDto, String bookingId) {
    Booking existingBooking =
        bookingRepository
            .findById(bookingId)
            .orElseThrow(() -> new InvalidDataException("Booking with ID not found: " + bookingId));

    existingBooking.setStartDate(bookingDto.startDate());
    existingBooking.setEndDate(bookingDto.endDate());

    if (BookingStatus.ACTIVE.equals(bookingDto.bookingStatus())) {
      checkAvailability(existingBooking);
    }

    List<GuestDetails> newGuestDetails =
        bookingDto.guestDetails().stream()
            .map(bookingMapper::toEntity)
            .peek(guestDetails -> guestDetails.setBooking(existingBooking))
            .toList();
    existingBooking.getGuestDetails().clear();
    existingBooking.getGuestDetails().addAll(newGuestDetails);
    existingBooking.setBookingStatus(bookingDto.bookingStatus());

    Booking savedUpdatedEntity = bookingRepository.save(existingBooking);
    log.info("Booking updated: {} ", savedUpdatedEntity);
    return bookingMapper.toDto(savedUpdatedEntity);
  }

  public void deleteBooking(String id) {
    Booking existingBooking =
        bookingRepository
            .findById(id)
            .orElseThrow(() -> new InvalidDataException("Booking with ID not found: " + id));

    existingBooking.setBookingStatus(BookingStatus.DELETED);
    bookingRepository.save(existingBooking);
  }

  private void checkAvailability(Booking booking) {
    List<Booking> overlappingBookings =
        bookingRepository
            .findBookingsBetweenDatesForProperty(
                booking.getStartDate(), booking.getEndDate(), booking.getProperty().getId())
            .stream()
            .filter(
                overlappingBooking -> !Objects.equals(overlappingBooking.getId(), booking.getId()))
            .toList();
    if (!overlappingBookings.isEmpty()) {
      throw new InvalidBookingException("Bookings overlapping.");
    }
    if (!blockService.checkBlockForDatesAndProperty(
        booking.getStartDate(), booking.getEndDate(), booking.getProperty().getId())) {
      throw new InvalidBookingException(
          "There are blocks for the given date range for this property.");
    }
  }
}
