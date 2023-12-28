package com.hostfully.interview.mapper;

import com.hostfully.interview.domain.dto.BookingDto;
import com.hostfully.interview.repository.entity.Booking;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class BookingMapper {
  public abstract Booking toEntity(BookingDto bookingDto);

  public abstract BookingDto toDto(Booking booking);

  @AfterMapping
  public Booking doAfterMapping(@MappingTarget Booking entity) {
    entity.getGuestDetails().forEach(guestDetails -> guestDetails.setBooking(entity));
    return entity;
  }
}
