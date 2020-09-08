package com.example.airbnblike.reservation.api;

import com.example.airbnblike.reservation.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/reservations")
public class ReservationApi {

    private final ReservationService reservationService;
}
