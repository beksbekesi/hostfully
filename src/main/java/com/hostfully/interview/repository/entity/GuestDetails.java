package com.hostfully.interview.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "guest_details")
public class GuestDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @NotNull
  private String name;

  @NotNull
  private Integer age;

  @ManyToOne
  @JoinColumn(name = "booking_id")
  private Booking booking;
}
