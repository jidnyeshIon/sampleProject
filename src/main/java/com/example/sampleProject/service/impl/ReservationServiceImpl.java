package com.example.sampleProject.service.impl;

import com.example.sampleProject.data.Guest;
import com.example.sampleProject.data.Reservation;
import com.example.sampleProject.data.Room;
import com.example.sampleProject.data.repository.ReservationRepository;
import com.example.sampleProject.data.repository.RoomRepository;
import com.example.sampleProject.service.GuestService;
import com.example.sampleProject.service.ReservationService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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


    @Override
    public List<Reservation> getAllReservations() {
        return this.reservationRepository.findAll();
    }

    @Override
    public List<Reservation> overlappingReservations(Date startDate, Date endDate) {
        List<Reservation> reservations = this.getAllReservations();

        List<Reservation> notVaccant =reservations.stream()
                .filter(reservation ->((reservation.getCheckInDate().before(startDate) && reservation.getCheckOutDate().after(endDate))
                        || (reservation.getCheckInDate().after(startDate) && reservation.getCheckOutDate().before(endDate))
                        || (reservation.getCheckInDate().before(startDate) && reservation.getCheckOutDate().before(endDate))
                        || (reservation.getCheckInDate().after(startDate) && reservation.getCheckOutDate().after(endDate))))
                .collect(Collectors.toList());

        return notVaccant;


    }

    // Method to book a room
    @Override
    public void saveBooking(long guestID, String roomNo,Date checkInDate, Date checkOutDate) {
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
