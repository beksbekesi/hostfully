package com.hostfully.interview.mapper;

import com.hostfully.interview.domain.dto.BookingDto;
import com.hostfully.interview.repository.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingMapper {
  Booking toEntity(BookingDto bookingDto);

  BookingDto toDto(Booking booking);
}
