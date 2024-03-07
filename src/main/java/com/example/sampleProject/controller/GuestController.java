package com.example.sampleProject.controller;

import com.example.sampleProject.data.Guest;
import com.example.sampleProject.service.GuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/guest")
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    // Endpoint to get all guests
    @GetMapping("/")
    public ResponseEntity<List<Guest>> getAllGuest(){
        return ResponseEntity.ok(this.guestService.getAllGuest());
    }

    // Endpoint to add a new guest
    @PostMapping("/")
    public  ResponseEntity<Guest> addGuest(@RequestBody  Guest guest){
        System.out.println("first Name " + guest.getFirstName());
        System.out.println("last Name " + guest.getLastName());
        System.out.println("email  " + guest.getEmailAddress());
        System.out.println("country Name " + guest.getCountry());

        // Return response entity with the added guest
        return ResponseEntity.ok(this.guestService.addGuest(guest));
    }




}
