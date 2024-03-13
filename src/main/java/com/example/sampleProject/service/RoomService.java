package com.example.sampleProject.service;

import com.example.sampleProject.data.Room;


import java.sql.Date;
import java.util.List;

public interface RoomService {

   public List<Room> getRooms() ;
   public  Room addRooms(Room room);

   public Room findRoomByRoomNumber(String roomNumber);


   public Room saveRoom(Room room);
}
