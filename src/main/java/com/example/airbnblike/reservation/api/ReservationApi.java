package com.example.airbnblike.reservation.api;

import com.example.airbnblike.rental.dto.RentalDto;
import com.example.airbnblike.reservation.dto.ReservationDto;
import com.example.airbnblike.reservation.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/reservations")
public class ReservationApi {

    private final ReservationService reservationService;

    @PostMapping()
    public ResponseEntity<String> addReservation(@RequestBody ReservationDto reservationDto) {
        reservationService.addReservation(reservationDto);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
