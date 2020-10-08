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
        newReservation.setCheckInDate(reservationDto.getCheckInDate());
        newReservation.setCheckOutDate(reservationDto.getCheckOutDate());
        newReservation.setMessageToHost(reservationDto.getMessageToHost());
        newReservation.setTotalAmount(reservationDto.getTotalAmount());
        newReservation.setRental(rentalService.getRentalByID(reservationDto.getRentalID()));
        newReservation.setStatus(reservationStatusRepository.getOne(reservationDto.getReservationStatusID()));
        newReservation.setGuestAppUser(authService.getUserByID(reservationDto.getGuestUserID()));

        reservationDto.getReservedRoomsIDs().forEach(ID -> {
            Room room = roomService.getRoomByID(ID);
            newReservation.addRoom(room);
        });

        Reservation savedReservation = reservationRepository.save(newReservation);
        reservationRepository.flush();

        savedReservation.getReservedRooms().forEach(room -> room.addReservation(savedReservation));
        rentalService.getRentalByID(reservationDto.getRentalID()).addReservation(savedReservation);
        authService.getUserByID(reservationDto.getGuestUserID()).addReservation(savedReservation);

        reservationRepository.save(savedReservation);
    }
}
