package com.example.airbnblike.room.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoomDto {

    private String name;
    private String description;
    private String price;
    private List<Long> amenitiesIDs;
    private List<Long> bedsIDs;
    private Long roomTypeID;
}
