package com.example.sampleProject.controller;

import com.example.sampleProject.data.Room;
import com.example.sampleProject.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
//    // Endpoint to get all rooms
//    @GetMapping("/")
//    public ResponseEntity<List<Room>> getRooms(){
//        // Return response entity with all rooms
//        return ResponseEntity.ok(this.roomService.getRooms());
//    }
//
//    // Endpoint to add a new room
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

    @GetMapping("/rooms")
    public String getRooms(Model model){
        model.addAttribute("rooms",this.roomService.getRooms() );
        return "hotel-rooms";
    }

    @GetMapping("/addnew")
    public String addNew(Model model){
        Room room = new Room();

        model.addAttribute("roomForm", room);
        return "add-room";
    }

    @PostMapping("/save")
    public String addRooms(@ModelAttribute("roomForm") Room room){
        System.out.println(room.toString());
       this.roomService.addRooms(room);
       return "redirect:index.html";
    }
}