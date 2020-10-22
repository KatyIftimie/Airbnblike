package com.example.airbnblike.room.repository;

import com.example.airbnblike.room.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Integer countAllBy();

}
