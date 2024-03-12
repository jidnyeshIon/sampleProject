package com.example.sampleProject.controller;

import com.example.sampleProject.data.Guest;
import com.example.sampleProject.data.Room;
import com.example.sampleProject.service.ReservationService;
import com.example.sampleProject.service.RoomService;
import com.example.sampleProject.utli.DateUtils;
import com.example.sampleProject.utli.MyRequestDTO;
import com.example.sampleProject.utli.RoomResStatus;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
//import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ReservationController {
     private  RoomResStatus localObj;
    private Date checkInDate;
    private Date checkOutDate;
     private String roomNo ;
    DateUtils dateUtils = new DateUtils();

    private final ReservationService reservationService;
    private final RoomService roomService;
    // Constructor injection for ReservationService and RoomService
    public ReservationController(ReservationService reservationService, RoomService roomService) {
        this.reservationService = reservationService;
        this.roomService = roomService;
    }

    @GetMapping("/chooseRoom")
    public String chooseRoom(Model model){
        Room room = new Room();
        model.addAttribute("room",room);
        return "get-room-choice";

    }
    @PostMapping("/saveRoomChoice")
    public String saveRoomChoice(@ModelAttribute("room") Room room){
        this.roomNo = room.getRoom_number();
        return "redirect:/addNewGuest";
    }
    @GetMapping("/saveBooking")
    @PostMapping("/saveBooking")
    public String saveBooking(@RequestParam("guestId" ) long guestId, Model model){
        System.out.println("GuestID : " + guestId);
        System.out.println("Room No : " + this.roomNo);
        Room bookedRoom = this.roomService.findRoomByRoomNumber(this.roomNo);
        System.out.println("Printing from saveBooking");
        System.out.println("Check in Date " + this.checkInDate);
        System.out.println("Check out Date " + this.checkOutDate);
        bookedRoom.setCheckInDate(new java.sql.Date(this.checkInDate.getTime()));
        bookedRoom.setCheckOutDate(new java.sql.Date(this.checkOutDate.getTime()));
        this.reservationService.saveBooking(guestId,roomNo);
        System.out.println("Room Booked successfully ");
        return "redirect:index.html";
    }

    @GetMapping("/book-room")
    public String getRequirement(Model model){
        RoomResStatus roomResStatus = new RoomResStatus();
        model.addAttribute("requirementInfo",roomResStatus );
        return "requirement-info";
    }


    @PostMapping("/checkAvailability")
    public String checkAvailability(@ModelAttribute("requirementInfo") RoomResStatus roomResStatus, Model model){
        this.localObj = roomResStatus;
         this.checkInDate = dateUtils.createDateFromDateString(roomResStatus.getCheckInDate());
         this.checkOutDate =  dateUtils.createDateFromDateString(roomResStatus.getCheckOutDate());

        System.out.println("Check in date " + checkInDate);
        System.out.println("Check Out date " + checkOutDate);
        System.out.println("Type " + roomResStatus.getBedType());

        List<Room> rooms = this.roomService.getRooms();

        List<Room> getFilteredRooms = (List<Room>) rooms.stream()
                .filter(room -> room.getCheckOutDate().before(checkInDate))
                .filter(room -> room.getBedInfo().equalsIgnoreCase(roomResStatus.getBedType()))
                .collect(Collectors.toList());

        model.addAttribute("rooms", getFilteredRooms);
        return "available-rooms";
    }
}
