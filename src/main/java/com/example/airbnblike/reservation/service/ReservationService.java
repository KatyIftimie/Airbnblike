package com.example.airbnblike.reservation.service;

import com.example.airbnblike.reservation.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
}
