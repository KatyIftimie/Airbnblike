package com.example.airbnblike.room.service;

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

    @Transactional
    public List<RoomType> getAllRoomTypes(){
        return  roomTypeRepository.findAll();
    }
}
