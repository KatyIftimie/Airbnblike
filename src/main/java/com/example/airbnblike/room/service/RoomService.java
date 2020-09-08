package com.example.airbnblike.room.service;

import com.example.airbnblike.room.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
}
