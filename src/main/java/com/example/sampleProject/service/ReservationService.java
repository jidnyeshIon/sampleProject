package com.example.sampleProject.service;

import com.example.sampleProject.data.Guest;
import com.example.sampleProject.data.Room;

import java.util.List;

public interface ReservationService {

    public List<Room> getAllVacantRooms();
    public void bookRoom(Room room, Guest guest);
}
