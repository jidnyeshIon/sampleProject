package com.example.sampleProject.service;

import com.example.sampleProject.data.Room;


import java.util.List;

public interface RoomService {

   public List<Room> getRooms() ;
   public  Room addRooms(Room room);

   public Room findRoomByRoomNumber(String roomNumber);




}
