package com.hostfully.interview.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.hostfully.interview.domain.dto.booking.BookingDto;
import com.hostfully.interview.domain.dto.booking.BookingStatus;
import com.hostfully.interview.mapper.BookingMapper;
import com.hostfully.interview.repository.BookingRepository;
import com.hostfully.interview.repository.PropertyRepository;
import com.hostfully.interview.repository.entity.Booking;
import com.hostfully.interview.repository.entity.Property;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class BookingServiceTest {

  private static final String PROPERTY_ID = "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa";
  @Mock private BookingRepository bookingRepository;

  @Mock private PropertyRepository propertyRepository;

  @Mock private BookingMapper bookingMapper;

  @Mock private BlockService blockService;

  @InjectMocks private BookingService bookingService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getBookingWorksWithValidData() {
    LocalDate startDate = LocalDate.parse("2024-01-01");
    LocalDate endDate = startDate.plusDays(5);
    String bookingId = "123";
    BookingDto bookingDto =
        new BookingDto(
            bookingId, startDate, endDate, BookingStatus.ACTIVE, new ArrayList<>(), PROPERTY_ID);
    Booking booking = new Booking();

    when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));
    when(bookingMapper.toDto(booking)).thenReturn(bookingDto);

    BookingDto result = bookingService.getBooking(bookingId);

    verify(bookingRepository, times(1)).findById(bookingId);
    verify(bookingMapper, times(1)).toDto(booking);
    assertNotNull(result);
    assertEquals(startDate, result.startDate());
    assertEquals(endDate, result.endDate());
    assertEquals(bookingId, result.id());
  }

  @Test
  void createBookingWorksWithValidData() {
    LocalDate startDate = LocalDate.parse("2024-01-01");
    LocalDate endDate = startDate.plusDays(5);
    String bookingId = "123";
    BookingDto bookingDto =
        new BookingDto(
            bookingId, startDate, endDate, BookingStatus.ACTIVE, new ArrayList<>(), PROPERTY_ID);
    Property property = new Property();
    property.setId(PROPERTY_ID);
    Booking booking = new Booking();
    booking.setProperty(property);

    when(propertyRepository.findById(anyString())).thenReturn(Optional.of(property));
    when(bookingMapper.toEntity(any(), any())).thenReturn(booking);
    when(bookingRepository.save(any())).thenReturn(booking);
    when(bookingMapper.toDto(any())).thenReturn(bookingDto);

    when(blockService.checkBlockForDatesAndProperty(any(), any(), any())).thenReturn(true);

    BookingDto result = bookingService.createBooking(bookingDto);

    verify(propertyRepository, times(1)).findById(anyString());
    verify(bookingMapper, times(1)).toEntity(any(), any());
    verify(bookingRepository, times(1)).save(any());
    verify(bookingMapper, times(1)).toDto(any());
    verify(blockService, times(1)).checkBlockForDatesAndProperty(any(), any(), any());
    assertNotNull(result);
    assertEquals(startDate, result.startDate());
    assertEquals(endDate, result.endDate());
    assertEquals(bookingId, result.id());
  }

  @Test
  void updateBookingWorksWithValidData() {
    String bookingId = "123";
    LocalDate startDate = LocalDate.parse("2024-01-01");
    LocalDate endDate = startDate.plusDays(5);
    BookingDto bookingDto =
        new BookingDto(
            bookingId, startDate, endDate, BookingStatus.ACTIVE, new ArrayList<>(), PROPERTY_ID);
    Booking existingBooking = new Booking();
    Property property = new Property();
    property.setId(PROPERTY_ID);
    existingBooking.setProperty(property);

    when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(existingBooking));
    when(bookingRepository.save(any())).thenReturn(existingBooking);
    when(bookingMapper.toDto(any())).thenReturn(bookingDto);

    when(blockService.checkBlockForDatesAndProperty(any(), any(), any())).thenReturn(true);

    BookingDto result = bookingService.updateBooking(bookingDto, bookingId);

    verify(bookingRepository, times(1)).findById(bookingId);
    verify(bookingRepository, times(1)).save(any());
    verify(bookingMapper, times(1)).toDto(any());
    verify(blockService, times(1)).checkBlockForDatesAndProperty(any(), any(), any());
    assertNotNull(result);
    assertNotNull(result);
    assertEquals(startDate, result.startDate());
    assertEquals(endDate, result.endDate());
    assertEquals(bookingId, result.id());
  }

  @Test
  void deleteBookingWorksWithValidData() {
    String bookingId = "123";
    Booking existingBooking = new Booking();

    when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(existingBooking));

    assertDoesNotThrow(() -> bookingService.deleteBooking(bookingId));

    verify(bookingRepository, times(1)).findById(bookingId);
    assertEquals(BookingStatus.DELETED, existingBooking.getBookingStatus());
    verify(bookingRepository, times(1)).save(existingBooking);
  }
}
