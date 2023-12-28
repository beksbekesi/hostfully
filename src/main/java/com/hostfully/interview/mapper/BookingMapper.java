package com.hostfully.interview.mapper;

import com.hostfully.interview.domain.dto.BookingDto;
import com.hostfully.interview.repository.entity.Booking;
import com.hostfully.interview.repository.entity.Property;
import org.mapstruct.*;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class BookingMapper {
  public abstract Booking toEntity(BookingDto bookingDto, @Context Property property);

  @Mapping(source = "property.id", target = "propertyId")
  public abstract BookingDto toDto(Booking booking);

  @AfterMapping
  public Booking doAfterMapping(@MappingTarget Booking entity) {
    entity.getGuestDetails().forEach(guestDetails -> guestDetails.setBooking(entity));
    return entity;
  }

  @BeforeMapping
  protected void doBeforeMapping(
      BookingDto bookingDto, @MappingTarget Booking entity, @Context Property property) {
    entity.setProperty(property);
    property.getBookings().add(entity);
  }
}
