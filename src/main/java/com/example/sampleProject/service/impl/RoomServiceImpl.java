package com.example.sampleProject.service.impl;

import com.example.sampleProject.data.Room;
import com.example.sampleProject.data.repository.RoomRepository;
import com.example.sampleProject.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> getRooms() {
        return this.roomRepository.findAll();
    }

    @Override
    public Room addRooms(Room room) {

            return this.roomRepository.save(room);
    }

    @Override
    public Room findRoomByRoomNumber(String roomNumber) {
        return this.roomRepository.findByRoomNumber(roomNumber);
    }
}
