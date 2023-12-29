package com.hostfully.interview.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "blocks")
public class Block {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @NotNull private LocalDate startDate;
  @NotNull private LocalDate endDate;
  private String reason;

  @ManyToOne
  @JoinColumn(name = "property_id")
  private Property property;
}
