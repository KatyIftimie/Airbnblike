package com.example.airbnblike.reservation.repository;

import com.example.airbnblike.reservation.model.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationStatusRepository extends JpaRepository<ReservationStatus, Long> {
}
