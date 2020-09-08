package com.example.airbnblike.repository;

import com.example.airbnblike.model.Room;
import com.example.airbnblike.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
