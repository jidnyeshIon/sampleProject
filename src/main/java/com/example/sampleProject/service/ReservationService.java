package com.example.sampleProject.service;

import com.example.sampleProject.data.Guest;
import com.example.sampleProject.data.Reservation;
import com.example.sampleProject.data.Room;


import java.util.Date;
import java.util.List;

public interface ReservationService {

    public List<Reservation> getAllReservations();
    public List<Reservation> overlappingReservations(Date startDate, Date endDate);
    public void saveBooking(long guestId, String roomNO,Date checkInDate, Date checkOutDate);
    public Reservation getReservationByRoomIdAndGuestId(long roomNo, long guestId);

    void deleteReservation(Reservation reservation);
}
