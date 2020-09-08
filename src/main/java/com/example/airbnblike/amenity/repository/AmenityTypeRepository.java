package com.example.airbnblike.amenity.repository;

import com.example.airbnblike.amenity.model.AmenityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmenityTypeRepository extends JpaRepository<AmenityType, Long> {
}
