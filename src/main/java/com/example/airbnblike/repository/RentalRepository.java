package com.example.airbnblike.repository;

import com.example.airbnblike.model.User;
import com.example.airbnblike.rental.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
}
