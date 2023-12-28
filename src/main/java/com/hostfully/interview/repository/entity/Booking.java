package com.hostfully.interview.repository.entity;

import com.hostfully.interview.domain.dto.BookingStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

  @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<GuestDetails> guestDetails = new ArrayList<>();

  @NotNull private String propertyId;
}
