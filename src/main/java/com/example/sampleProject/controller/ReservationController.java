package com.example.sampleProject.controller;

import com.example.sampleProject.data.Guest;
import com.example.sampleProject.data.Room;
import com.example.sampleProject.service.ReservationService;
import com.example.sampleProject.service.RoomService;
import com.example.sampleProject.utli.MyRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final RoomService roomService;
    // Constructor injection for ReservationService and RoomService
    public ReservationController(ReservationService reservationService, RoomService roomService) {
        this.reservationService = reservationService;
        this.roomService = roomService;
    }
    // Endpoint to get the status of all vacant rooms
    @GetMapping("/status")
    public ResponseEntity<List<Room>> getAllVacantRooms(){
        return ResponseEntity.ok(this.reservationService.getAllVacantRooms());
    }
    // Endpoint to book a room
    @PostMapping("/book")
    public void bookroom(@RequestBody MyRequestDTO obj){

        System.out.println("Room no : " + obj.getRoomNo());
        System.out.println("guest name " + obj.getGuest().getFirstName() );
        // Extract room number from request DTO
        String room_number = obj.getRoomNo();
        Room room = this.roomService.findRoomByRoomNumber(room_number);

        // Extract guest information from request DTO
        Guest guest = obj.getGuest();

        // Book the room with the given guest
        this.reservationService.bookRoom(room,guest);

    }
}
