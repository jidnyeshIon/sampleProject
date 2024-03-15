package com.example.sampleProject.controller;

import com.example.sampleProject.data.BedType;
import com.example.sampleProject.data.Guest;
import com.example.sampleProject.data.Reservation;
import com.example.sampleProject.data.Room;
import com.example.sampleProject.service.GuestService;
import com.example.sampleProject.service.ReservationService;
import com.example.sampleProject.service.RoomService;
import com.example.sampleProject.utli.DTO;
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
    private final GuestService guestService;
    private final DateUtils dateUtils = new DateUtils();

    // Constructor injection for ReservationService and RoomService
    public ReservationController(ReservationService reservationService, RoomService roomService, GuestService guestService) {
        this.reservationService = reservationService;
        this.roomService = roomService;

        this.guestService = guestService;
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

        // Validation for checkein checkout dates
        Date currentDate = new Date();
        if(checkOutDate.before(checkInDate) || (checkOutDate.before(currentDate)  || checkInDate.before(currentDate))){
            System.out.println("Invalid Date Selected !!!");
            return "redirect:/book-room ";
        }

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

    @GetMapping("/cancellationDetails")
    public String getCancellationDetails(Model model){
        DTO dto = new DTO();
        model.addAttribute("dto", dto);
        return "get-cancellation-details";

    }
    @PostMapping("/cancelBooking")
    public String cancelBooking(@ModelAttribute("dto") DTO dto){
        String roomNo = dto.getRoomNo();
        Guest guest = dto.getGuest();
//        System.out.println(guest.toString());

//        System.out.println("Guest email :" + guest.getEmailAddress());
        try {
            guest = this.guestService.findGuestByEmailAddress(guest.getEmailAddress());
        } catch (Exception e) {

            System.out.println("No booking found for the guest");
            return "redirect:index.html";

        }
//        System.out.println(guest.toString());

        // If the guest is not found
        if (guest == null) {
            return "redirect:index.html";
        }

        long guestId = guest.getId();
        Room room = this.roomService.findRoomByRoomNumber(roomNo);

        // No room available with givent
        if (room == null) {
            System.out.println("Invalid room Number");
            return "redirect:index.html";

        }
        long roomID = room.getId();
        Reservation reservation = this.reservationService.getReservationByRoomIdAndGuestId(roomID, guestId);
        room.setCheckInDate(new java.sql.Date(new Date().getTime()));
        room.setCheckOutDate(new java.sql.Date(new Date().getTime()));
        room = this.roomService.saveRoom(room);
        this.reservationService.deleteReservation(reservation);
        return "redirect:index.html";



    }
}
