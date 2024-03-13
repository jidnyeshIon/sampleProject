package com.example.sampleProject.utli;

import com.example.sampleProject.data.Guest;


public class DTO {
   private String roomNo;
    private Guest guest;

    public DTO(String roomNo, Guest guest) {
        this.roomNo = roomNo;
        this.guest = guest;
    }

    public DTO() {

    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
