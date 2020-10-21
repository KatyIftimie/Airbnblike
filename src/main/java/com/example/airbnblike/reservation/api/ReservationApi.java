package com.example.airbnblike.reservation.api;


import com.example.airbnblike.reservation.dto.ReservationDto;
import com.example.airbnblike.reservation.model.Reservation;
import com.example.airbnblike.reservation.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/reservations")
@CrossOrigin(origins = "http://localhost:3000")
public class ReservationApi {

    private final ReservationService reservationService;

    @PostMapping()
    public ResponseEntity<String> addReservation(@RequestBody ReservationDto reservationDto) {
        reservationService.addReservation(reservationDto);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
    
    @GetMapping("/user/{user_id}")
    public List<Reservation> getUserReservations(@PathVariable("user_id") String userId) {
        return reservationService.getAllByUserId(Long.valueOf(userId));
    }

    @GetMapping("/bookedRooms")
    public List<Reservation> getBookedRooms(@RequestBody ReservationDto reservationDto ) {
        return reservationService.getExistingBookings(reservationDto);
    }
}
