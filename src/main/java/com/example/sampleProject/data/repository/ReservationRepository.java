package com.example.sampleProject.data.repository;

import com.example.sampleProject.data.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Iterable<Reservation> findReservationByDate(Date date);
}
