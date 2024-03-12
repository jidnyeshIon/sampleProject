package com.example.sampleProject.controller;

import com.example.sampleProject.data.Guest;
import com.example.sampleProject.data.Room;
import com.example.sampleProject.service.GuestService;
import com.example.sampleProject.service.ReservationService;
import com.example.sampleProject.service.RoomService;
import com.example.sampleProject.service.impl.ReservationServiceImpl;
import com.example.sampleProject.utli.MyRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller

public class GuestController {

    private final GuestService guestService;
    private final ReservationService reservationService;
    private final RoomService roomService;

    public GuestController(GuestService guestService, ReservationService reservationService, RoomService roomService) {
        this.guestService = guestService;
        this.reservationService = reservationService;

        this.roomService = roomService;
    }

    // Endpoint to get all guests
    @GetMapping("/allGuest")
    public ResponseEntity<List<Guest>> getAllGuest(){
        return ResponseEntity.ok(this.guestService.getAllGuest());
    }

    // Endpoint to add a new guest
    @GetMapping("/addNewGuest")
    public String addNewGuest(Model model){
       Guest guest = new Guest();
        model.addAttribute("guest", guest);
        return "add-guest";
    }

    @PostMapping("/saveGuest")
    public String addRooms(@ModelAttribute("guest") Guest guest, RedirectAttributes redirectAttributes){
        System.out.println("Guest email : " +guest.getEmailAddress());
      Guest newAddedGuest =   this.guestService.addGuest(guest);

        redirectAttributes.addAttribute("guestId", guest.getId());
//        model.addAttribute("guestId")

        return "redirect:/saveBooking";



    }




}
