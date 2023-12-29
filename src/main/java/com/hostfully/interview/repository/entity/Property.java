package com.hostfully.interview.repository.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "properties")
public class Property {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String name;

  @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Booking> bookings = new ArrayList<>();

  @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Block> blocks = new ArrayList<>();
}
