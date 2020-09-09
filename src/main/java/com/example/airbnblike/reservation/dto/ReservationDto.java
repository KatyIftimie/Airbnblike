package com.example.airbnblike.reservation.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReservationDto {
    private Date checkInDate;
    private Date checkOutDate;
    private Float totalAmount;
    private String messageToHost;
    private Long rentalID;
    private Long reservationStatusID;
    private List<Long> reservedRoomsIDs;
    private Long guestUserID;
}
