package com.hostfully.interview.repository.entity;

import com.hostfully.interview.domain.dto.BookingStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "bookings")
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @NotNull private LocalDate startDate;

  @NotNull private LocalDate endDate;

  @NotNull
  @Enumerated(EnumType.STRING)
  private BookingStatus bookingStatus;

  @NotNull private String guestId;

  @NotNull private String propertyId;
}
