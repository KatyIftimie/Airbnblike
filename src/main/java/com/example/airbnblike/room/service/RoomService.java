package com.example.airbnblike.room.service;

import com.example.airbnblike.room.model.RoomType;
import com.example.airbnblike.amenity.model.Amenity;
import com.example.airbnblike.amenity.service.AmenityService;
import com.example.airbnblike.bed.model.Bed;
import com.example.airbnblike.bed.service.BedService;
import com.example.airbnblike.room.dto.RoomDto;
import com.example.airbnblike.room.model.Room;
import com.example.airbnblike.room.model.RoomType;
import com.example.airbnblike.room.repository.RoomRepository;
import com.example.airbnblike.room.repository.RoomTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @AllArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final AmenityService amenityService;
    private final BedService bedService;

    public RoomType getRoomTypeByID(Long ID) {
        return roomTypeRepository.getOne(ID);
    }

    public Room createModelFromDto(Room newRoom, RoomDto roomDto) {
        newRoom.setName(roomDto.getName());
        newRoom.setDescription(roomDto.getDescription());
        newRoom.setPrice(Float.valueOf(roomDto.getPrice()));

        roomDto.getAmenitiesIDs().forEach(ID -> {
            Amenity amenity = amenityService.getAmenityByID(ID);
            newRoom.addAmenity(amenity);
        });

        roomDto.getBedsIDs().forEach(ID -> {
            Bed bed = bedService.getBedByID(ID);
            newRoom.addBed(bed);
        });

        newRoom.setType(getRoomTypeByID(roomDto.getRoomTypeID()));
        return newRoom;
    }

    public void save(Room room) {
        roomRepository.save(room);
    }

    @Transactional
    public List<RoomType> getAllRoomTypes(){
        return  roomTypeRepository.findAll();
    }
}
