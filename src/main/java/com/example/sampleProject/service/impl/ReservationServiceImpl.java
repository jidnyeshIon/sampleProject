package com.example.sampleProject.service.impl;

import com.example.sampleProject.data.Guest;
import com.example.sampleProject.data.Reservation;
import com.example.sampleProject.data.Room;
import com.example.sampleProject.data.repository.ReservationRepository;
import com.example.sampleProject.data.repository.RoomRepository;
import com.example.sampleProject.service.GuestService;
import com.example.sampleProject.service.ReservationService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;
    private final GuestService guestService;

    public ReservationServiceImpl(RoomRepository roomRepository, ReservationRepository reservationRepository, GuestService guestService) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
        this.guestService = guestService;
    }

    // Method to get all vacant rooms


    // Method to book a room
    @Override
    public void saveBooking(long guestID, String roomNo) {
        Room room = this.roomRepository.findByRoomNumber(roomNo);


        // Create a reservation entry
        Reservation reservation = new Reservation();
        reservation.setRoomId(room.getId()); // Set room ID
        reservation.setGuestId(guestID); // Set guest ID
        Date currentDate = new Date();
        reservation.setDate(new java.sql.Date(currentDate.getTime())); // Set reservation date
        // Save the reservation entry
        this.reservationRepository.save(reservation);
    }

    @Override
    public Reservation getReservationByRoomIdAndGuestId(long roomNo, long guestId) {
        return this.reservationRepository.getReservationByRoomIdAndGuestId(roomNo,guestId);
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        this.reservationRepository.delete(reservation);
    }
}
