package com.example.sampleProject.controller;

import com.example.sampleProject.data.BedType;
import com.example.sampleProject.data.Room;
import com.example.sampleProject.service.ReservationService;
import com.example.sampleProject.service.RoomService;
import com.example.sampleProject.utli.DateUtils;
import com.example.sampleProject.utli.RoomResStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ReservationController {

    private Date checkInDate;
    private Date checkOutDate;
    private String roomNo;
    private final ReservationService reservationService;
    private final RoomService roomService;
    private final DateUtils dateUtils = new DateUtils();

    // Constructor injection for ReservationService and RoomService
    public ReservationController(ReservationService reservationService, RoomService roomService) {
        this.reservationService = reservationService;
        this.roomService = roomService;
    }

    // Display the form to choose a room
    @GetMapping("/chooseRoom")
    public String chooseRoom(Model model) {
        Room room = new Room();
        model.addAttribute("room", room);
        return "get-room-choice";
    }

    // Save the selected room choice and redirect to add new guest page
    @PostMapping("/saveRoomChoice")
    public String saveRoomChoice(@ModelAttribute("room") Room room) {
        this.roomNo = room.getRoom_number();
        return "redirect:/addNewGuest";
    }

    // Save the booking details
    @GetMapping("/saveBooking")
    @PostMapping("/saveBooking")
    public String saveBooking(@RequestParam("guestId") long guestId, Model model) {
        System.out.println("GuestID : " + guestId);
        System.out.println("Room No : " + this.roomNo);
        System.out.println("Printing from saveBooking");
        System.out.println("Check in Date " + this.checkInDate);
        System.out.println("Check out Date " + this.checkOutDate);
        Room bookedRoom = this.roomService.findRoomByRoomNumber(this.roomNo);
        bookedRoom.setCheckInDate(new java.sql.Date(this.checkInDate.getTime()));
        bookedRoom.setCheckOutDate(new java.sql.Date(this.checkOutDate.getTime()));
        this.reservationService.saveBooking(guestId, roomNo);
        System.out.println("Room Booked successfully ");
        return "redirect:index.html";
    }

    // Display the form to input booking requirements
    @GetMapping("/book-room")
    public String getRequirement(Model model) {
        RoomResStatus roomResStatus = new RoomResStatus();
        model.addAttribute("requirementInfo", roomResStatus);
        return "requirement-info";
    }

    // Check room availability based on booking requirements
    @PostMapping("/checkAvailability")
    public String checkAvailability(@ModelAttribute("requirementInfo") RoomResStatus roomResStatus, Model model) {

        this.checkInDate = dateUtils.createDateFromDateString(roomResStatus.getCheckInDate());
        this.checkOutDate = dateUtils.createDateFromDateString(roomResStatus.getCheckOutDate());
        BedType bedType = BedType.valueOf(roomResStatus.getBedType());

        System.out.println("Check in date " + checkInDate);
        System.out.println("Check Out date " + checkOutDate);
        System.out.println("Type " + roomResStatus.getBedType());

        // Get the list of all rooms from the room service
        List<Room> rooms = this.roomService.getRooms();

        // Filter the rooms based on check-out date and bed type
        List<Room> filteredRooms = rooms.stream()
                .filter(room -> room.getCheckOutDate().before(checkInDate))     // Filter rooms where the check-out date is before the check-in date
                .filter(room -> room.getBedInfo() == bedType)     // Filter rooms based on bed type
                .collect(Collectors.toList());

        model.addAttribute("rooms", filteredRooms);
        return "available-rooms";
    }
}
