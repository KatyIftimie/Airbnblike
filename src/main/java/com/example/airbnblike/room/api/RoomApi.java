package com.example.airbnblike.room.api;

import com.example.airbnblike.room.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/rooms")
public class RoomApi {

    private final RoomRepository roomRepository;
}
