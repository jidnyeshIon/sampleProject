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

    public ReservationController(ReservationService reservationService, RoomService roomService) {
        this.reservationService = reservationService;
        this.roomService = roomService;
    }

    @GetMapping("/status")
    public ResponseEntity<List<Room>> getAllVacantRooms(){
        return ResponseEntity.ok(this.reservationService.getAllVacantRooms());
    }

    @PostMapping("/book")
    public void bookroom(@RequestBody MyRequestDTO obj){

        System.out.println("Room no : " + obj.getRoomNo());
        System.out.println("guest name " + obj.getGuest().getFirstName() );
        String room_number = obj.getRoomNo();
        Room room = this.roomService.findRoomByRoomNumber(room_number);
        Guest guest = obj.getGuest();

        this.reservationService.bookRoom(room,guest);

    }
}
