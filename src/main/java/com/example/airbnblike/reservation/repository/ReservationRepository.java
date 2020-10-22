package com.example.airbnblike.reservation.repository;

import com.example.airbnblike.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
    @Transactional
    List<Reservation> findAllByGuestAppUser_Id(Long userID);

    @Transactional
    List<Reservation> findAllByCheckInDateAfterAndCheckOutDateBeforeAndRentalId(Date checkIn, Date checkOut, long rentalID);

    @Transactional
    List<Reservation> findAllByCheckInDateIsBeforeAndCheckOutDateIsAfterAndRentalId(Date checkIn, Date checkIn2, long rentalID);

}
