package com.example.airbnblike.rental.repository;

import com.example.airbnblike.rental.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    @Transactional
    List<Rental> findAllByAddress_CountryIgnoreCase(String country);

}
