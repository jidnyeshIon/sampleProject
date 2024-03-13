package com.example.sampleProject.data.repository;

import com.example.sampleProject.data.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT res FROM Reservation res WHERE res.roomId = :roomId AND res.guestId = :guestId")
    Reservation getReservationByRoomIdAndGuestId(@Param("roomId") long roomId, @Param("guestId") long guestId);


}
