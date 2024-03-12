package com.example.sampleProject.controller;

import com.example.sampleProject.data.Room;
import com.example.sampleProject.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

//@RestController
//@RequestMapping("/rooms")
//public class RoomController {
//
//    private final RoomService roomService;
//
//    // Constructor injection for RoomService
//    public RoomController(RoomService roomService) {
//        this.roomService = roomService;
//    }
//
//
//    @GetMapping("/")
//    public ResponseEntity<List<Room>> getRooms(){
//        // Return response entity with all rooms
//        return ResponseEntity.ok(this.roomService.getRooms());
//    }
//
//
//    @PostMapping("/")
//    public ResponseEntity<Room> addRoom(@RequestBody Room formData){
//        // Print room number and room name for debugging
//        System.out.println("Room Number: " + formData.getRoom_number());
//        System.out.println("Room Name: " + formData.getName());
//
//        // Return response entity with the added room
//        return ResponseEntity.ok(this.roomService.addRooms(formData));
//    }
//}
@Controller
//@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }
    // Endpoint to get all rooms
    @GetMapping("/rooms")
    public String getRooms(Model model){
        model.addAttribute("rooms",this.roomService.getRooms() );
        return "hotel-rooms";
    }
    // Endpoint to add a new room
    @GetMapping("/addnew")
    public String addNew(Model model){
        Room room = new Room();

        model.addAttribute("roomForm", room);
        return "add-room";
    }
    //Save the newly added room
    @PostMapping("/save")
    public String addRooms(@ModelAttribute("roomForm") Room room){
        System.out.println(room.toString());
        Date dt = new Date();
        room.setCheckInDate(new java.sql.Date(dt.getTime()));
        room.setCheckOutDate(new java.sql.Date(dt.getTime()));

       this.roomService.addRooms(room);
       return "redirect:index.html";
    }
}