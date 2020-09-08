package com.example.airbnblike.repository;

import com.example.airbnblike.rental.model.Rental;
import com.example.airbnblike.rental.model.RentalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalTypeRepository extends JpaRepository<RentalType, Long> {
}
