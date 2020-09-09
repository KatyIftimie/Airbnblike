package com.example.airbnblike.rental.dto;

import com.example.airbnblike.address.dto.AddressDto;
import com.example.airbnblike.room.dto.RoomDto;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.List;

@Data
public class RentalDto {

    private MultipartFile image;
    private String name;
    private String description;
    private Instant checkInTime;
    private Instant checkOutTime;
    private List<RoomDto> roomsDtoList;
    private List<Long> amenitiesIDs;
    private AddressDto addressDto;
    private Long rentalTypeID;
    private Long hostUserID;
}
