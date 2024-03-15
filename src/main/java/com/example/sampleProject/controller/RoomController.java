package com.example.sampleProject.controller;

import com.example.sampleProject.data.Room;
import com.example.sampleProject.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
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
       this.roomService.addRooms(room);
       return "redirect:index.html";
    }
}