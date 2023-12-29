package com.hostfully.interview.repository;

import com.hostfully.interview.repository.entity.Block;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BlockRepository extends JpaRepository<Block, String> {
  @Query(
      "SELECT b FROM Block b WHERE b.startDate <= :endDate AND b.endDate >= :startDate AND property.id = :propertyId")
  List<Block> findBlocksBetweenDatesForProperty(
      @Param("startDate") LocalDate startDate,
      @Param("endDate") LocalDate endDate,
      @Param("propertyId") String propertyId);
}
