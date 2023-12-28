package com.hostfully.interview.repository;

import com.hostfully.interview.repository.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, String> {}
