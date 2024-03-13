package com.example.sampleProject.service;

import com.example.sampleProject.data.Guest;
import com.example.sampleProject.data.Reservation;
import com.example.sampleProject.data.Room;

import java.util.List;

public interface ReservationService {


    public void saveBooking(long guestId, String roomNO);
    public Reservation getReservationByRoomIdAndGuestId(long roomNo, long guestId);

    void deleteReservation(Reservation reservation);
}
