package com.example.airbnblike.reservation.service;

import com.example.airbnblike.auth.service.AuthService;
import com.example.airbnblike.rental.service.RentalService;
import com.example.airbnblike.reservation.dto.ReservationDto;
import com.example.airbnblike.reservation.model.Reservation;
import com.example.airbnblike.reservation.repository.ReservationRepository;
import com.example.airbnblike.reservation.repository.ReservationStatusRepository;
import com.example.airbnblike.room.model.Room;
import com.example.airbnblike.room.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class ReservationService {
    private final RentalService rentalService;
    private final ReservationStatusRepository reservationStatusRepository;
    private final AuthService authService;
    private final RoomService roomService;
    private final ReservationRepository reservationRepository;



    public  void addReservation(ReservationDto reservationDto){
        Reservation newReservation= new Reservation();
        newReservation.setCheckInDate(reservationDto.getCheckInTime());
        newReservation.setCheckOutDate(reservationDto.getCheckOutTime());
        newReservation.setMessageToHost(reservationDto.getMessageToHost());
        newReservation.setTotalAmount(reservationDto.getTotalAmount());
        newReservation.setRental(rentalService.getRentalByID(reservationDto.getRentalID()));
        newReservation.setStatus(reservationStatusRepository.getOne(reservationDto.getReservationStatusID()));
        newReservation.setGuestUser(authService.getUserByID(reservationDto.getGuestUserID()));

        reservationDto.getReservedRoomsID().forEach(ID ->{
            Room room=roomService.getRoomByID(ID);
            newReservation.reserveRoom(room);
        });


        Reservation savedReservation=reservationRepository.save(newReservation);
        reservationRepository.flush();
        reservationRepository.save(savedReservation);

    }
}
