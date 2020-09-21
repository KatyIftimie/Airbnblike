package com.example.airbnblike.rental.dto;

import com.example.airbnblike.address.dto.AddressDto;
import com.example.airbnblike.rental.service.CustomDeserializer;
import com.example.airbnblike.room.dto.RoomDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


import java.time.Instant;
import java.util.List;

@Data
public class RentalDto {



//    private List<MultipartFile> images;
    private String name;
    private String description;
   @JsonDeserialize(using = CustomDeserializer.class) private Instant checkInTime;
     @JsonDeserialize(using = CustomDeserializer.class) private Instant checkOutTime;
    private List<RoomDto> roomsDtoList;
    private List<Long> amenitiesIDs;
    private AddressDto addressDto;
    private Long rentalTypeID;
    private Long hostUserID;




}
