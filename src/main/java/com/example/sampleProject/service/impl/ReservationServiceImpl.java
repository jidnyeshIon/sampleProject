package com.example.sampleProject.service.impl;

import com.example.sampleProject.data.Guest;
import com.example.sampleProject.data.Reservation;
import com.example.sampleProject.data.Room;
import com.example.sampleProject.data.repository.GuestRepository;
import com.example.sampleProject.data.repository.ReservationRepository;
import com.example.sampleProject.data.repository.RoomRepository;
import com.example.sampleProject.service.GuestService;
import com.example.sampleProject.service.ReservationService;
import com.example.sampleProject.service.RoomService;
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

    @Override
    public List<Room> getAllVacantRooms() {
        return this.roomRepository.findVacantRooms();
    }

    @Override
    public void bookRoom(Room room, Guest guest) {
        room.setBooked(true);
        roomRepository.save(room);

        this.guestService.addGuest(guest);

        Reservation res = new Reservation();
        res.setRoomId(room.getId());
        res.setGuestId(guest.getId());
        Date dt = new Date();
        res.setDate(new java.sql.Date(dt.getTime()));
        this.reservationRepository.save(res);







    }
}
