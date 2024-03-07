package com.example.sampleProject.controller;

import com.example.sampleProject.data.Room;
import com.example.sampleProject.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;


    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }
//    @RequestMapping(method = RequestMethod.GET)
//    public String getRooms(Model model){
//        model.addAttribute("rooms" , this.roomService.getRooms());
//        return "hotel-rooms";
//    }
    @GetMapping("/")
    public ResponseEntity<List<Room>> getRooms(){
        return  ResponseEntity.ok(this.roomService.getRooms());
    }
    @PostMapping("/" )
    public ResponseEntity<Room> addRoom( @RequestBody Room formData){
        System.out.println("Room Number "  + formData.getRoom_number());
        System.out.println("room Name "  + formData.getName());
       return ResponseEntity.ok(this.roomService.addRooms(formData));

    }

}
