package com.example.airbnblike.room.api;

import com.example.airbnblike.room.model.RoomType;
import com.example.airbnblike.room.repository.RoomRepository;
import com.example.airbnblike.room.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/rooms")
public class RoomApi {

    private final RoomService roomService;

    @Transactional
    @GetMapping("/room-types")
    public List<RoomType> getAllRoomTypes(){
        return roomService.getAllRoomTypes();
    }
}
