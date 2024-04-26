package com.example.sampleProject.controller;

import com.example.sampleProject.data.Guest;
import com.example.sampleProject.service.GuestService;
import com.example.sampleProject.service.ReservationService;
import com.example.sampleProject.service.RoomService;
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

    // Constructor injection for GuestService, ReservationService, and RoomService
    public GuestController(GuestService guestService, ReservationService reservationService, RoomService roomService) {
        this.guestService = guestService;
        this.reservationService = reservationService;
        this.roomService = roomService;
    }

    // Endpoint to get all guests
    @GetMapping("/allGuest")
    public String getAllGuest(Model model){
        model.addAttribute("guests", this.guestService.getAllGuest());
        return "hotel-guests";
    }

    // Endpoint to add a new guest
    @GetMapping("/addNewGuest")
    public String addNewGuest(Model model) {
        Guest guest = new Guest();
        model.addAttribute("guest", guest);
        return "add-guest";
    }

    // Endpoint to save a new guest
    @PostMapping("/saveGuest")
    public String addRooms(@ModelAttribute("guest") Guest guest, RedirectAttributes redirectAttributes) {
        System.out.println("Guest email : " + guest.getEmailAddress());

        // If the guest is not present then only add the guest
        long guestId;
        Guest alreadyExistGuest = this.guestService.findGuestByEmailAddress(guest.getEmailAddress());
        if(alreadyExistGuest == null){
            Guest newAddedGuest = this.guestService.addGuest(guest);
            guestId = newAddedGuest.getId();
        }
        else{
            guestId = alreadyExistGuest.getId();
        }

        System.out.println("Guest id : " + guest.getId());
        redirectAttributes.addAttribute("guestId",guestId);
        return "redirect:/saveBooking";
    }

    //Sample change for git
    //Doing Changes in main for showing in new_sample
    // doing changes in mew_sample for learning stash

}
