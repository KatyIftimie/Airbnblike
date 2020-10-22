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
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service @AllArgsConstructor
public class ReservationService {
    private final RentalService rentalService;
    private final ReservationStatusRepository reservationStatusRepository;
    private final AuthService authService;
    private final RoomService roomService;
    private final ReservationRepository reservationRepository;



    public void addReservation(ReservationDto reservationDto){
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
//        rentalService.getRentalByID(reservationDto.getRentalID()).addReservation(savedReservation);
        authService.getUserByID(reservationDto.getGuestUserID()).addReservation(savedReservation);

        reservationRepository.save(savedReservation);
    }
    
    @Transactional
    public List<Reservation> getAllByUserId(Long userId) {
        return reservationRepository.findAllByGuestAppUser_Id(userId);
    }

    @Transactional
    public Set<Long> getExistingBookings(ReservationDto reservationDto) {

        //TO DO query in repository
        //TO DO .... WRITE TEST!!!
        Date checkIn = reservationDto.getCheckInDate();
        Date checkOut = reservationDto.getCheckOutDate();
        Long id = reservationDto.getRentalID();
        List <Reservation> list1 = reservationRepository.findAllByCheckInDateAfterAndCheckOutDateBeforeAndRentalId(checkIn, checkOut, id);
        List <Reservation> list2 = reservationRepository.findAllByCheckInDateIsBeforeAndCheckOutDateIsAfterAndRentalId(checkIn, checkIn, id);
        List <Reservation> list3 = reservationRepository.findAllByCheckInDateIsBeforeAndCheckOutDateIsAfterAndRentalId(checkOut, checkOut, id);
        List<Reservation> newList = Stream.of(list1, list2, list3)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        List<List<Room>> bookedRooms = new ArrayList<>();
        for (Reservation reservation : newList){
            bookedRooms.add( reservation.getReservedRooms());
        }
        List<Long> rooms_ids = new ArrayList<>();
        for (List<Room> roomList: bookedRooms ){
            for (Room room : roomList) {
                rooms_ids.add(room.getId());
            }
        }
        Set<Long> ids = new HashSet<Long>(rooms_ids);
        return  ids;
    }
}
