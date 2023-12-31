package com.hostfully.interview.repository;

import com.hostfully.interview.repository.entity.Booking;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {

  @Query(
      "SELECT b FROM Booking b WHERE b.startDate <= :endDate AND b.endDate >= :startDate AND bookingStatus = 'ACTIVE' AND property.id = :propertyId")
  List<Booking> findBookingsBetweenDatesForProperty(
      @Param("startDate") LocalDate startDate,
      @Param("endDate") LocalDate endDate,
      @Param("propertyId") String propertyId);
}
