package com.example.sampleProject.data.repository;


import com.example.sampleProject.data.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {

    // Query to find a room by its room number
    @Query("SELECT r FROM Room r WHERE r.room_number = :value")
    Room findByRoomNumber(@Param("value") String room_number);
}
