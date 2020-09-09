package com.example.airbnblike.reservation.dto;

import com.example.airbnblike.room.dto.RoomDto;
import com.example.airbnblike.room.model.Room;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReservationDto {
    private Date checkInTime;
    private Date checkOutTime;
    private Float totalAmount;
    private String messageToHost;
    private Long rentalID;
    private List<Long> reservedRoomsID;
    private Long reservationStatusID;
    private Long guestUserID;
}
